package com.g.fod.apps.rt;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import com.g.fod.apps.rt.exception.SlaveQueryEmptyException;
import com.g.fod.endpoints.mexc.domain.Dict.OpenType;
import com.g.fod.endpoints.mexc.domain.Dict.OrderSide;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelReq;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelResp;
import com.g.fod.endpoints.mexc.endpoints.QueryOrderEndpoint.QueryResp;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.g.fod.apps.rt.Command.Cancel;
import com.g.fod.apps.rt.Command.Got;
import com.g.fod.apps.rt.Command.Make;
import com.g.fod.apps.rt.Command.ReduceOnly;
import com.g.fod.apps.rt.Command.Take;
import com.g.fod.endpoints.mexc.MexcApiSpec;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.Price;
import com.p.common.base.domain.Vol;
import com.p.common.base.time.EpochMillis;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Schedulers;

import static java.time.Duration.ofMillis;
import static reactor.util.retry.Retry.backoff;

@Slf4j
@Getter
@Setter
@JsonTypeInfo(property = "type", use = Id.NAME)
@JsonSubTypes({
  @Type(value = Take.class, name = "command.take"),
  @Type(value = Make.class, name = "command.make"),
  @Type(value = Cancel.class, name = "command.cancel"),
  @Type(value = Got.class, name = "command.get"),
  @Type(value = ReduceOnly.class, name = "command.reduce")
})
public abstract class Command<R> {

  protected EpochMillis ts;
  protected Consumer<R> consumer;

  protected Command(Consumer<R> consumer) {
    this.consumer = consumer;
    this.ts = EpochMillis.of(System.currentTimeMillis());
  }

  public abstract Mono<R> exec(MexcApiSpec spec);

  public static class PlaceOrderCommand extends Command<OrderId> {

    private final Function<MexcApiSpec, Mono<OrderId>> function;

    public PlaceOrderCommand(Consumer<OrderId> consumer, Function<MexcApiSpec, Mono<OrderId>> function) {
      super(consumer);
      this.function = function;
    }

    @Override
    public Mono<OrderId> exec(MexcApiSpec spec) {
      val millis = System.currentTimeMillis();
      return function.apply(spec)
        .publishOn(Schedulers.boundedElastic())
        .doOnSuccess(orderId -> {
          log.info("round.trip.to_ack = {}", System.currentTimeMillis() - millis);
          consumer.accept(orderId);
        });
    }
  }

  @Setter
  @Getter
  public static class Take extends PlaceOrderCommand {

    public Take(Consumer<OrderId> consumer, String symbol, int vol, OpenType openType) {
      super(consumer, spec -> spec.openLong(symbol, vol, openType));
    }
  }

  @Setter
  @Getter
  public static class Make extends PlaceOrderCommand {

    public Make(Consumer<OrderId> consumer, String symbol, int vol, OpenType openType, Price px) {
      super(consumer, spec -> spec.openLong(symbol, px, vol, openType));
    }
  }

  @Setter
  @Getter
  public static class ReduceOnly extends PlaceOrderCommand {

    private static final Consumer<OrderId> NOOP_CONSUMER = orderId -> {};

    public ReduceOnly(Long positionId, String symbol, Vol vol, OpenType openType) {
      super(NOOP_CONSUMER,
        spec -> spec.reduceOnly(positionId, symbol, OrderSide.CLOSE_LONG, openType, vol.getValue().intValue(),
          Price.ZERO));
    }
  }

  public static class Got extends Command<QueryResp> {

    private final OrderId orderId;

    protected Got(Consumer<QueryResp> consumer, OrderId orderId) {
      super(consumer);
      this.orderId = orderId;
    }

    @Override
    public Mono<QueryResp> exec(MexcApiSpec spec) {
      val startCancelTs = System.currentTimeMillis();
      return spec.getByOrdId(orderId)
        .handle((BiConsumer<QueryResp, SynchronousSink<QueryResp>>) (queryResp, synchronousSink) -> {
          if (queryResp.getData() != null) {
            synchronousSink.next(queryResp);
          } else {
            synchronousSink.error(new SlaveQueryEmptyException());
          }
        }).retryWhen(
          backoff(10, ofMillis(50)).jitter(0.75).filter(SlaveQueryEmptyException.class::isInstance))
        .doOnSuccess(queryResp -> {
          log.info("round.trip.to_got = {}", System.currentTimeMillis() - startCancelTs);
          consumer.accept(queryResp);
        });
    }
  }

  @Setter
  @Getter
  public static class Cancel extends Command<CancelResp> {

    private static final Consumer<CancelResp> NOOP_CONSUMER = cancelResp -> {};
    private CancelReq cancelReq;

    public Cancel(OrderId... orderIds) {
      super(NOOP_CONSUMER);
      this.cancelReq = new CancelReq().ordId(orderIds);
    }

    @Override
    public Mono<CancelResp> exec(MexcApiSpec spec) {
      val millis = System.currentTimeMillis();
      return spec.cancel(cancelReq)
        .doOnSuccess(cancelResp -> {
          log.info("round.trip.to_cancel = {}", System.currentTimeMillis() - millis);
          consumer.accept(cancelResp);
        });
    }
  }
}
