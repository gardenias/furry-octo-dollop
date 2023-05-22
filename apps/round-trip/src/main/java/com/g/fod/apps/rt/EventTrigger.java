package com.g.fod.apps.rt;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.function.Consumer;

import com.g.fod.apps.rt.Command.Cancel;
import com.g.fod.apps.rt.Command.Got;
import com.g.fod.apps.rt.Command.Make;
import com.g.fod.endpoints.mexc.domain.Dict.OpenType;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelResp;
import com.g.fod.endpoints.mexc.endpoints.QueryOrderEndpoint.QueryResp;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.Price;
import com.p.common.base.time.EpochMillis;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class EventTrigger implements SmartInitializingSingleton {

  private static final String SYMBOL = "BTC_USDT";
  private final EventTranslatorOneArg<CommandWrapperEvent, OrderId> cancelCommandTranslator;
  private final EventTranslatorOneArg<CommandWrapperEvent, OrderId> queryCommandTranslator;
  private final Consumer<Long> consumer;

  @Autowired
  public EventTrigger(Disruptor<CommandWrapperEvent> disruptor) {
    this.cancelCommandTranslator = (event, sequence, orderId) -> {
      Command<CancelResp> command = new Cancel(orderId);
      event.setCommand(command);
    };

    // publish cancel command after get
    Consumer<QueryResp> orderConsumer =
      order -> disruptor.publishEvent(cancelCommandTranslator, order.getData().getOrderId());

    this.queryCommandTranslator = (event, sequence, orderId) -> {
      Command<QueryResp> command = new Got(orderConsumer, orderId);
      event.setCommand(command);
    };

    // publish query command after place
    Consumer<OrderId> orderIdConsumer = orderId -> disruptor.publishEvent(queryCommandTranslator, orderId);

    this.consumer = seq -> disruptor.publishEvent((event, sequence) -> {
      val make = new Make(orderIdConsumer, SYMBOL, 1, OpenType.CROSS, Price.of(new BigDecimal("1")));
      event.setCommand(make);
      event.setEpochMillis(new EpochMillis(System.currentTimeMillis()));
    });
  }

  public void take() {}

  public void closePosition() {

  }

  @Override
  public void afterSingletonsInstantiated() {
    Flux.interval(Duration.ofSeconds(5)).subscribe(consumer);
  }
}
