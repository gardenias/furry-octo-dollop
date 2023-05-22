package com.g.fod.endpoints.binance.spec.impl;

import java.util.Map;

import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.extern.slf4j.Slf4j;
import com.google.common.collect.Maps;

import com.g.fod.endpoints.binance.rest.market.spot.KlinesEndpoint;
import com.g.fod.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesReq;
import com.g.fod.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint;
import com.g.fod.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.g.fod.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookReq;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookResp;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerReq;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint;
import com.g.fod.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesResp;
import com.g.fod.endpoints.binance.spec.AbstractRestSpec;
import com.g.fod.endpoints.binance.spec.BinanceMarketRestSpec;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BinanceMarketRestSpecImpl extends AbstractRestSpec implements BinanceMarketRestSpec {

  private final OldTradeLookupEndpoint oldTradeLookupEndpoint;
  private final RecentTradesListEndpoint recentTradesListEndpoint;
  private final KlinesEndpoint klinesEndpoint;
  private final OrderBookEndpoint orderBookEndpoint;
  private final SymbolPriceTickerEndpoint symbolPriceTickerEndpoint;
  private final OrderBookUsdFuturesEndpoint orderBookUsdFuturesEndpoint;
  private final RecentTradesListUsdFuturesEndpoint recentTradesListUsdFuturesEndpoint;
  private final SymbolPriceTickerUsdFuturesEndpoint symbolPriceTickerUsdFuturesEndpoint;

  public BinanceMarketRestSpecImpl(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer);
    this.oldTradeLookupEndpoint = new OldTradeLookupEndpoint(webClient, signatureProducer);
    this.recentTradesListEndpoint = new RecentTradesListEndpoint(webClient, signatureProducer);
    this.klinesEndpoint = new KlinesEndpoint(webClient, signatureProducer);
    this.orderBookEndpoint = new OrderBookEndpoint(webClient, signatureProducer);
    this.symbolPriceTickerEndpoint = new SymbolPriceTickerEndpoint(webClient, signatureProducer);
    this.orderBookUsdFuturesEndpoint = new OrderBookUsdFuturesEndpoint(webClient, signatureProducer);
    this.recentTradesListUsdFuturesEndpoint = new RecentTradesListUsdFuturesEndpoint(webClient, signatureProducer);
    this.symbolPriceTickerUsdFuturesEndpoint = new SymbolPriceTickerUsdFuturesEndpoint(webClient, signatureProducer);

  }

  @Override
  public Flux<OldTradeLookupResp> oldTradeLookupSpot(OldTradeLookupReq req) {
    return oldTradeLookupEndpoint.execFlux(req, OldTradeLookupResp.class, req);
  }

  @Override
  public Flux<RecentTradesListResp> recentTradesListSpot(RecentTradesListReq req) {
    Map<String, Object> map = Maps.newHashMap();
    map.put("symbol", "BTCUSDT");
    return recentTradesListEndpoint.execFlux(req, RecentTradesListResp.class, map);
  }

  @Override
  public Mono<String> klinesSpot(KlinesReq req) {
    return klinesEndpoint.execFluxString(req, String.class, req);
  }

  @Override
  public Mono<OrderBookResp> orderBookSpot(OrderBookReq req) {
    return orderBookEndpoint.exec(req, OrderBookResp.class);
  }

  @Override
  public Flux<SymbolPriceTickerResp> symbolPriceTickerSpot(SymbolPriceTickerReq req) {
    return symbolPriceTickerEndpoint.execFlux(req, SymbolPriceTickerResp.class);
  }

  @Override
  public Mono<OrderBookUsdFuturesResp> orderBookUsdFutures(OrderBookUsdFuturesReq req) {
    return orderBookUsdFuturesEndpoint.exec(req, OrderBookUsdFuturesResp.class);
  }

  @Override
  public Flux<RecentTradesListUsdFuturesResp> recentTradesListUsdFutures(RecentTradesListUsdFuturesReq req) {
    return recentTradesListUsdFuturesEndpoint.execFlux(req, RecentTradesListUsdFuturesResp.class);
  }

  @Override
  public Flux<SymbolPriceTickerUsdFuturesResp> symbolPriceTickerUsdFutures(SymbolPriceTickerUsdFuturesReq req) {
    return symbolPriceTickerUsdFuturesEndpoint.execFlux(req, SymbolPriceTickerUsdFuturesResp.class);
  }
}
