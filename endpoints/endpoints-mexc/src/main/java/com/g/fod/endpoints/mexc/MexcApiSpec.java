package com.g.fod.endpoints.mexc;

import com.g.fod.endpoints.mexc.domain.Dict.OpenType;
import com.g.fod.endpoints.mexc.domain.Dict.OrderSide;
import com.g.fod.endpoints.mexc.domain.Dict.OrderType;
import com.g.fod.endpoints.mexc.endpoints.AccountAssetsEndpoint;
import com.g.fod.endpoints.mexc.endpoints.CancelAllEndpoint;
import com.g.fod.endpoints.mexc.endpoints.CancelAllEndpoint.CancelAllReq;
import com.g.fod.endpoints.mexc.endpoints.CancelAllEndpoint.CancelAllResp;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelReq;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelResp;
import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint;
import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitReq;
import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitResp;
import com.g.fod.endpoints.mexc.endpoints.PingEndpoint;
import com.g.fod.endpoints.mexc.endpoints.PingEndpoint.PingResp;
import com.g.fod.endpoints.mexc.endpoints.QueryActiveOrderEndpoint;
import com.g.fod.endpoints.mexc.endpoints.QueryOrderEndpoint;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

import com.g.common.endpoints.core.rest.EmptyReq;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Resp.RespLoggerOnFailConsumer;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.Price;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class MexcApiSpec {

  private final OrderSubmitEndpoint placeOrderEndpoint;
  private final AccountAssetsEndpoint accountAssetsEndpoint;
  private final CancelAllEndpoint cancelAllEndpoint;
  private final CancelByOrderIdEndpoint cancelByOrderIdEndpoint;
  private final QueryOrderEndpoint queryOrderEndpoint;
  private final QueryActiveOrderEndpoint queryActiveOrderEndpoint;

  private final PingEndpoint pingEndpoint;

  public MexcApiSpec(WebClient webClient, HeadersProducer headersProducer) {
    this.placeOrderEndpoint = new OrderSubmitEndpoint(webClient, headersProducer);
    this.accountAssetsEndpoint = new AccountAssetsEndpoint(webClient, headersProducer);
    this.cancelAllEndpoint = new CancelAllEndpoint(webClient, headersProducer);
    this.cancelByOrderIdEndpoint = new CancelByOrderIdEndpoint(webClient, headersProducer);
    this.queryOrderEndpoint = new QueryOrderEndpoint(webClient, headersProducer);
    this.queryActiveOrderEndpoint = new QueryActiveOrderEndpoint(webClient, headersProducer);

    this.pingEndpoint = new PingEndpoint(webClient);
  }

  public long ping() {
    val pingResp = pingEndpoint.exec(new EmptyReq(), PingResp.class).blockOptional();
    return pingResp.map(PingResp::getData).orElse(-1L);
  }

  public Mono<OrderId> openLong(String symbol, int vol) {
    return openLong(symbol, vol, OpenType.CROSS);
  }

  public Mono<OrderId> openLong(String symbol, int vol, OpenType openType) {
    return place(openType, OrderType.MARKET, OrderSide.OPEN_LONG, symbol, Price.ZERO, vol);
  }

  public Mono<OrderId> openLong(String symbol, Price px, int vol) {
    return openLong(symbol, px, vol, OpenType.CROSS);
  }

  public Mono<OrderId> openLong(String symbol, Price px, int vol, OpenType openType) {
    return place(openType, OrderType.LIMIT, OrderSide.OPEN_LONG, symbol, px, vol);
  }

  public Mono<OrderId> openShort(String symbol, int vol) {
    return openShort(symbol, vol, OpenType.CROSS);
  }

  public Mono<OrderId> openShort(String symbol, int vol, OpenType openType) {
    return place(openType, OrderType.MARKET, OrderSide.OPEN_SHORT, symbol, Price.ZERO, vol);
  }

  public Mono<OrderId> openShort(String symbol, Price px, int vol) {
    return openShort(symbol, px, vol, OpenType.CROSS);
  }

  public Mono<OrderId> openShort(String symbol, Price px, int vol, OpenType openType) {
    return place(openType, OrderType.LIMIT, OrderSide.OPEN_SHORT, symbol, px, vol);
  }

  public Mono<OrderId> closeShort(String symbol, int vol) {
    return closeShort(symbol, vol, OpenType.CROSS);
  }

  public Mono<OrderId> closeShort(String symbol, int vol, OpenType openType) {
    return place(openType, OrderType.MARKET, OrderSide.CLOSE_SHORT, symbol, Price.ZERO, vol);
  }

  public Mono<OrderId> closeShort(String symbol, Price px, int vol) {
    return closeShort(symbol, px, vol, OpenType.CROSS);
  }

  public Mono<OrderId> closeShort(String symbol, Price px, int vol, OpenType openType) {
    return place(openType, OrderType.LIMIT, OrderSide.CLOSE_SHORT, symbol, px, vol);
  }

  public Mono<OrderId> closeLong(String symbol, int vol) {
    return closeLong(symbol, vol, OpenType.CROSS);
  }

  public Mono<OrderId> closeLong(String symbol, int vol, OpenType openType) {
    return place(openType, OrderType.MARKET, OrderSide.CLOSE_LONG, symbol, Price.ZERO, vol);
  }

  public Mono<OrderId> closeLong(String symbol, Price px, int vol) {
    return closeLong(symbol, px, vol, OpenType.CROSS);
  }

  public Mono<OrderId> closeLong(String symbol, Price px, int vol, OpenType openType) {
    return place(openType, OrderType.LIMIT, OrderSide.CLOSE_LONG, symbol, px, vol);
  }

  public Mono<OrderId> reduceOnly(Long positionId, String symbol,
    OrderSide side, OpenType openType, int vol, Price px) {
    val req =
      new SubmitReq(symbol).setReduceOnly(true).setPrice(px).setVol(vol).setPositionId(positionId)
        .setType(px.signum() > 0 ? OrderType.LIMIT : OrderType.MARKET)
        .setSide(side).setOpenType(openType);

    return place(req).mapNotNull(SubmitResp::data);
  }

  public Mono<OrderId> place(OpenType openType, OrderType type, OrderSide side, String symbol, Price px, int vol) {
    val req =
      new SubmitReq(symbol).setPrice(px).setVol(vol)
        .setType(type).setSide(side).setOpenType(openType);

    return place(req).mapNotNull(SubmitResp::data);
  }

  public Mono<SubmitResp> place(SubmitReq req) {
    if (StringUtils.isBlank(req.getExternalOid())) {
      req.setExternalOid("" + System.currentTimeMillis());
    }
    Mono<SubmitResp> respMono = placeOrderEndpoint.exec(req, SubmitResp.class);
    return respMono.doOnNext(new RespLoggerOnFailConsumer(req, log));
  }

  public Mono<CancelResp> cancel(OrderId... ordIds) {
    return cancel(new CancelReq().ordId(ordIds));
  }

  public Mono<CancelResp> cancel(CancelReq req) {
    return cancelByOrderIdEndpoint.exec(req, CancelResp.class);
  }

  public Mono<CancelAllResp> cancelAll() {
    return cancelAll(null);
  }

  public Mono<CancelAllResp> cancelAll(String symbol) {
    return cancel(new CancelAllReq(symbol));
  }

  public Mono<CancelAllResp> cancel(CancelAllEndpoint.CancelAllReq req) {
    return cancelAllEndpoint.exec(req, CancelAllResp.class);
  }

  public Mono<AccountAssetsEndpoint.AssetsResp> assets(AccountAssetsEndpoint.AssetsReq req) {
    return accountAssetsEndpoint.exec(req, AccountAssetsEndpoint.AssetsResp.class);
  }

  public Mono<QueryOrderEndpoint.QueryResp> getByOrdId(OrderId ordId) {
    return queryOrderEndpoint.exec(new QueryOrderEndpoint.QueryReq(ordId), QueryOrderEndpoint.QueryResp.class, ordId);
  }

  public Mono<QueryActiveOrderEndpoint.QueryResp> getAll(String symbol) {
    return queryActiveOrderEndpoint.exec(new QueryActiveOrderEndpoint.QueryReq(symbol),
      QueryActiveOrderEndpoint.QueryResp.class, symbol);
  }
}
