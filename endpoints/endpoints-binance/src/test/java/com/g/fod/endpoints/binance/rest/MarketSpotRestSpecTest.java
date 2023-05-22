package com.g.fod.endpoints.binance.rest;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import com.g.fod.endpoints.binance.CommParams;
import com.g.fod.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesReq;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookReq;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookResp;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerReq;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerResp;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class MarketSpotRestSpecTest {

  @Test
  void testKlines() {
    KlinesReq req = new KlinesReq();
    req.setSymbol("BTCUSDT");
    req.setInterval("1m");
    Mono<String> monp = CommParams.marketRestSpecTestSpot.klinesSpot(req);
    log.info("result:{}", JSONObject.toJSONString(monp.block()));
  }

  @Test
  void testOrderBook() {
    OrderBookReq req = new OrderBookReq();
    req.setSymbol("BTCUSDT");
    Mono<OrderBookResp> monp = CommParams.marketRestSpecTestSpot.orderBookSpot(req);
    log.info("result:{}", JSONObject.toJSONString(monp.block()));
  }

  @Test
  void testRecentTradesList() {
    RecentTradesListReq req = new RecentTradesListReq();
    req.setSymbol("BTCUSDT");
    Flux<RecentTradesListResp> flux = CommParams.marketRestSpecTestSpot.recentTradesListSpot(req);
    log.info("result:{}", JSONObject.toJSONString(flux.collectList().block()));
  }

  @Test
  void testSymbolPriceTicker() {
    SymbolPriceTickerReq req = new SymbolPriceTickerReq();
    req.setSymbol("BTCUSDT");
    Flux<SymbolPriceTickerResp> flux = CommParams.marketRestSpecTestSpot.symbolPriceTickerSpot(req);
    log.info("result:{}", JSONObject.toJSONString(flux.collectList().block()));
  }
}
