package com.g.fod.endpoints.binance.rest;

import com.g.fod.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import com.g.fod.endpoints.binance.CommParams;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class TradeUsdRestSpecTest {

  @Test
  void testPlaceOrder() {
    PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderReq req = new PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderReq();
    req.setSymbol("BTCUSDT");
    req.setSide("SELL");
    req.setType("LIMIT");
    req.setTimeInForce("GTC");
    req.setQuantity("0.01");
    req.setPrice("95000");
    req.setTimestamp(System.currentTimeMillis());
    Mono<PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderResp> mono = CommParams.tradeRestSpecTestUsd.placeUsdFuturesOrder(req);
    log.info("result:{}", JSONObject.toJSONString(mono.block()));
  }

  // TODO 签名有问题，待调试
  @Test
  void testMultiplePlaceOrderOrder() {
    PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersReq req = new PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersReq();
    PlaceMultipleUsdFuturesOrdersEndpoint.Order order = new PlaceMultipleUsdFuturesOrdersEndpoint.Order();
    //  "[{\"symbol\": \"BTCUSDT\",\"side\":\"BUY\",\"positionSide\":\"LONG\",\"type\":\"LIMIT\",\"newClientOrderId\":\"wanlu_dev_0324\",\"quantity\":\"1\",\"price\": \"8000\",\"timeInForce\":\"GTC\"},\
    order.setSymbol("BTCUSDT");
    order.setSide("BUY");
    order.setType("LIMIT");
    order.setPositionSide("LONG");
    order.setNewClientOrderId("wanlu_dev_0324");
    order.setQuantity("1");
    order.setPrice("8000");
    order.setTimeInForce("GTC");
    req.setBatchOrders(Lists.newArrayList(order));
    req.setTimestamp(System.currentTimeMillis());
    req.setRecvWindow(60000L);
    Flux<PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersResp> flux = CommParams.tradeRestSpecTestUsd.placeMultipleUsdFuturesOrders(req);
    log.info("result:{}", JSONObject.toJSONString(flux.collectList().block()));
  }

  @Test
  void testQueryOrder() {
    QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderReq req = new QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderReq();
    req.setOrderId(3065887335L);
    req.setSymbol("BTCUSDT");
    req.setTimestamp(System.currentTimeMillis());
    req.setRecvWindow(60000L);
    Mono<QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderResp> mono = CommParams.tradeRestSpecTestUsd.queryUsdFuturesOrder(req);
    log.info("result:{}", JSONObject.toJSONString(mono.block()));
  }

  @Test
  void testCancelOrder() {
    CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderReq req = new CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderReq();
    req.setOrderId(3065887335L);
    req.setSymbol("BTCUSDT");
    req.setTimestamp(System.currentTimeMillis());
    Mono<CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderResp> mono = CommParams.tradeRestSpecTestUsd.cancelUsdFuturesOrder(req);
    log.info("result:{}", JSONObject.toJSONString(mono.block()));
  }
}
