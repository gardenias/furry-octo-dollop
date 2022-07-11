package com.p.quant.endpoints.binance.rest;

import com.alibaba.fastjson.JSONObject;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderResp;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint.Order;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersResp;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderResp;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderResp;

import com.p.quant.endpoints.binance.CommParams;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class TradeUsdRestSpecTest {


    @Test
    void testPlaceOrder() {
        PlaceUsdFuturesOrderReq req = new PlaceUsdFuturesOrderReq();
        req.setSymbol("BTCUSDT");
        req.setSide( "SELL");
        req.setType("LIMIT");
        req.setTimeInForce( "GTC");
        req.setQuantity("0.01");
        req.setPrice("95000");
        req.setTimestamp(System.currentTimeMillis());
        Mono<PlaceUsdFuturesOrderResp> mono = CommParams.tradeRestSpecTestUsd.placeUsdFuturesOrder(req);
        log.info("result:{}", JSONObject.toJSONString(mono.block()));
    }
    // TODO 签名有问题，待调试
    @Test
    void testMultiplePlaceOrderOrder() {
        PlaceMultipleUsdFuturesOrdersReq req = new PlaceMultipleUsdFuturesOrdersReq();
        Order order = new Order();
        //  "[{\"symbol\": \"BTCUSDT\",\"side\":\"BUY\",\"positionSide\":\"LONG\",\"type\":\"LIMIT\",\"newClientOrderId\":\"wanlu_dev_0324\",\"quantity\":\"1\",\"price\": \"8000\",\"timeInForce\":\"GTC\"},\
        order.setSymbol("BTCUSDT");
        order.setSide( "BUY");
        order.setType("LIMIT");
        order.setPositionSide("LONG");
        order.setNewClientOrderId("wanlu_dev_0324");
        order.setQuantity("1");
        order.setPrice("8000");
        order.setTimeInForce( "GTC");
        req.setBatchOrders(Lists.newArrayList(order));
        req.setTimestamp(System.currentTimeMillis());
        req.setRecvWindow(60000L);
        Flux<PlaceMultipleUsdFuturesOrdersResp> flux = CommParams.tradeRestSpecTestUsd.placeMultipleUsdFuturesOrders(req);
        log.info("result:{}", JSONObject.toJSONString(flux.collectList().block()));
    }

    @Test
    void testQueryOrder() {
        QueryUsdFuturesOrderReq req = new QueryUsdFuturesOrderReq();
         req.setOrderId(3065887335L);
        req.setSymbol("BTCUSDT");
        req.setTimestamp(System.currentTimeMillis());
        req.setRecvWindow(60000L);
        Mono<QueryUsdFuturesOrderResp> mono = CommParams.tradeRestSpecTestUsd.queryUsdFuturesOrder(req);
        log.info("result:{}", JSONObject.toJSONString(mono.block()));
    }

    @Test
    void testCancelOrder() {
        CancelUseFuturesOrderReq req = new CancelUseFuturesOrderReq();
        req.setOrderId(3065887335L);
        req.setSymbol("BTCUSDT");
        req.setTimestamp(System.currentTimeMillis());
        Mono<CancelUseFuturesOrderResp> mono = CommParams.tradeRestSpecTestUsd.cancelUsdFuturesOrder(req);
        log.info("result:{}", JSONObject.toJSONString(mono.block()));
    }
}
