package com.g.fod.endpoints.binance.rest;

import com.g.fod.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderReq;
import com.g.fod.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderResp;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import com.g.fod.endpoints.binance.CommParams;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@Slf4j
public class TradeMarginRestSpecTest {

  @Test
  void testPlaceOrder() {
    PlaceMarginAccountOrderReq req = new PlaceMarginAccountOrderReq();
    req.setSymbol("BNBUSDT");
    req.setSide("SELL");
    req.setType("MARKET");
    req.setQuantity("0.1");
    req.setTimestamp(System.currentTimeMillis());
    Mono<PlaceMarginAccountOrderResp> mono = CommParams.tradeRestSpecSpot.placeMarginAccountOrder(req);
    log.info("result:{}", JSONObject.toJSONString(mono.block()));
  }

}
