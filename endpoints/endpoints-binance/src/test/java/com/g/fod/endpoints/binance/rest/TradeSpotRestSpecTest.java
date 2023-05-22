package com.g.fod.endpoints.binance.rest;

import java.math.BigDecimal;

import com.g.fod.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import com.g.fod.endpoints.binance.CommParams;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@Slf4j
public class TradeSpotRestSpecTest {

  @Test
  void testPlaceOrder() {
    PlaceSpotOrderEndpoint.PlaceSpotOrderReq req = new PlaceSpotOrderEndpoint.PlaceSpotOrderReq();
    req.setSymbol("BTCUSDT");
    req.setSide("SELL");
    req.setType("LIMIT");
    req.setTimeInForce("GTC");
    req.setQuantity("0.01");
    req.setPrice(new BigDecimal(95000));
    req.setTimestamp(System.currentTimeMillis());
    Mono<PlaceSpotOrderEndpoint.PlaceSpotOrderResp> mono = CommParams.tradeRestSpecTestSpot.placeSpotOrder(req);
    log.info("result:{}", JSONObject.toJSONString(mono.block()));
  }

  @Test
  void testQueryOrder() {
    QuerySpotOrderEndpoint.QuerySpotOrderReq req = new QuerySpotOrderEndpoint.QuerySpotOrderReq();
    req.setOrigClientOrderId("DGrA0qeQD7WmB6sWlqyLo3");
    req.setSymbol("BTCUSDT");
    req.setTimestamp(System.currentTimeMillis());
    Mono<QuerySpotOrderEndpoint.QuerySpotOrderResp> mono = CommParams.tradeRestSpecTestSpot.querySpotOrder(req);
    log.info("result:{}", JSONObject.toJSONString(mono.block()));
  }

  @Test
  void testCancelOrder() {
    CancelSpotOrderEndpoint.CancelSpotOrderReq req = new CancelSpotOrderEndpoint.CancelSpotOrderReq();
    req.setOrigClientOrderId("DGrA0qeQD7WmB6sWlqyLo3");
    req.setSymbol("BTCUSDT");
    req.setTimestamp(System.currentTimeMillis());
    Mono<CancelSpotOrderEndpoint.CancelSpotOrderResp> mono = CommParams.tradeRestSpecTestSpot.cancelSpotOrder(req);
    log.info("result:{}", JSONObject.toJSONString(mono.block()));
  }
}
