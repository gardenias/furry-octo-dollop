package com.p.quant.endpoints.binance.rest;

import com.alibaba.fastjson.JSONObject;
import com.p.quant.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderReq;
import com.p.quant.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderResp;
import com.p.quant.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderReq;
import com.p.quant.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderResp;
import com.p.quant.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderReq;
import com.p.quant.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderResp;

import com.p.quant.endpoints.binance.CommParams;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
public class TradeSpotRestSpecTest {
    
    @Test
    void testPlaceOrder() {
        PlaceSpotOrderReq req = new PlaceSpotOrderReq();
        req.setSymbol("BTCUSDT");
        req.setSide( "SELL");
        req.setType("LIMIT");
        req.setTimeInForce( "GTC");
        req.setQuantity("0.01");
        req.setPrice( new BigDecimal(95000));
        req.setTimestamp(System.currentTimeMillis());
        Mono<PlaceSpotOrderResp> mono = CommParams.tradeRestSpecTestSpot.placeSpotOrder(req);
        log.info("result:{}", JSONObject.toJSONString(mono.block()));
    }
    @Test
    void testQueryOrder() {
        QuerySpotOrderReq req = new QuerySpotOrderReq();
        req.setOrigClientOrderId("DGrA0qeQD7WmB6sWlqyLo3");
        req.setSymbol("BTCUSDT");
        req.setTimestamp(System.currentTimeMillis());
        Mono<QuerySpotOrderResp> mono = CommParams.tradeRestSpecTestSpot.querySpotOrder(req);
        log.info("result:{}", JSONObject.toJSONString(mono.block()));
    }
    @Test
    void testCancelOrder() {
        CancelSpotOrderReq req = new CancelSpotOrderReq();
        req.setOrigClientOrderId("DGrA0qeQD7WmB6sWlqyLo3");
        req.setSymbol("BTCUSDT");
        req.setTimestamp(System.currentTimeMillis());
        Mono<CancelSpotOrderResp> mono = CommParams.tradeRestSpecTestSpot.cancelSpotOrder(req);
        log.info("result:{}", JSONObject.toJSONString(mono.block()));
    }
}
