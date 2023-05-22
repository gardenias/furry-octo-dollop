package com.g.fod.endpoints.binance.rest;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import com.g.fod.endpoints.binance.CommParams;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesResp;
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
