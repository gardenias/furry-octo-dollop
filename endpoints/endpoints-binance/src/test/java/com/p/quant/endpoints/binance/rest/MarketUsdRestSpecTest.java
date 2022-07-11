package com.p.quant.endpoints.binance.rest;

import com.alibaba.fastjson.JSONObject;
import com.p.quant.endpoints.binance.CommParams;
import com.p.quant.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesReq;
import com.p.quant.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookResp;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import com.p.quant.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerReq;
import com.p.quant.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerResp;

import com.p.quant.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesReq;

import com.p.quant.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesResp;

import com.p.quant.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesReq;

import com.p.quant.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesResp;

import com.p.quant.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesReq;

import com.p.quant.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesResp;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class MarketUsdRestSpecTest {

    
    @Test
    void testOrderBook() {
        OrderBookUsdFuturesReq req = new OrderBookUsdFuturesReq();
        req.setSymbol("BTCUSDT");
        Mono<OrderBookUsdFuturesResp> monp = CommParams.marketRestSpecTestUsd.orderBookUsdFutures(req);
        log.info("result:{}", JSONObject.toJSONString(monp.block()));
    }
    @Test
    void testRecentTradesList() {
        RecentTradesListUsdFuturesReq req = new RecentTradesListUsdFuturesReq();
        req.setSymbol("BTCUSDT");
        Flux<RecentTradesListUsdFuturesResp> flux = CommParams.marketRestSpecTestUsd.recentTradesListUsdFutures(req);
        log.info("result:{}", JSONObject.toJSONString(flux.collectList().block()));
    }
    @Test
    void testSymbolPriceTicker() {
        SymbolPriceTickerUsdFuturesReq req = new SymbolPriceTickerUsdFuturesReq();
        req.setSymbol("BTCUSDT");
        Flux<SymbolPriceTickerUsdFuturesResp> flux = CommParams.marketRestSpecTestUsd.symbolPriceTickerUsdFutures(req);
        log.info("result:{}", JSONObject.toJSONString(flux.collectList().block()));
    }
}
