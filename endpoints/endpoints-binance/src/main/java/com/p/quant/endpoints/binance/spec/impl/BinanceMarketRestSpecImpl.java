package com.p.quant.endpoints.binance.spec.impl;

import com.google.common.collect.Maps;

import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.market.spot.KlinesEndpoint;
import com.p.quant.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesReq;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;

import com.p.quant.endpoints.binance.rest.market.spot.OrderBookEndpoint;
import com.p.quant.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookReq;
import com.p.quant.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookResp;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import com.p.quant.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint;
import com.p.quant.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerReq;
import com.p.quant.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerResp;
import com.p.quant.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint;
import com.p.quant.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesReq;
import com.p.quant.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesResp;
import com.p.quant.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint;
import com.p.quant.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesReq;
import com.p.quant.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesResp;
import com.p.quant.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint;
import com.p.quant.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesReq;
import com.p.quant.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesResp;
import com.p.quant.endpoints.binance.spec.AbstractRestSpec;
import com.p.quant.endpoints.binance.spec.BinanceMarketRestSpec;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

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

    public BinanceMarketRestSpecImpl(WebClient webClient,  SignatureProducer signatureProducer) {
        super(webClient, signatureProducer);
        this.oldTradeLookupEndpoint = new OldTradeLookupEndpoint(webClient,signatureProducer);
        this.recentTradesListEndpoint = new RecentTradesListEndpoint(webClient,signatureProducer);
        this.klinesEndpoint = new KlinesEndpoint(webClient,signatureProducer);
        this.orderBookEndpoint = new OrderBookEndpoint(webClient,signatureProducer);
        this.symbolPriceTickerEndpoint = new SymbolPriceTickerEndpoint(webClient,signatureProducer);
        this.orderBookUsdFuturesEndpoint = new OrderBookUsdFuturesEndpoint(webClient,signatureProducer);
        this.recentTradesListUsdFuturesEndpoint = new RecentTradesListUsdFuturesEndpoint(webClient,signatureProducer);
        this.symbolPriceTickerUsdFuturesEndpoint = new SymbolPriceTickerUsdFuturesEndpoint(webClient,signatureProducer);

    }

    @Override
    public Flux<OldTradeLookupResp> oldTradeLookupSpot(OldTradeLookupReq req) {
        return oldTradeLookupEndpoint.execFlux(req, OldTradeLookupResp.class,req);
    }

    @Override
    public Flux<RecentTradesListResp> recentTradesListSpot(RecentTradesListReq req) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("symbol", "BTCUSDT");
        return recentTradesListEndpoint.execFlux(req, RecentTradesListResp.class,map);
    }

    @Override
    public Mono<String> klinesSpot(KlinesReq req) {
        return klinesEndpoint.execFluxString(req, String.class,req);
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
        return orderBookUsdFuturesEndpoint.exec(req,OrderBookUsdFuturesResp.class);
    }

    @Override
    public Flux<RecentTradesListUsdFuturesResp> recentTradesListUsdFutures(RecentTradesListUsdFuturesReq req) {
        return recentTradesListUsdFuturesEndpoint.execFlux(req,RecentTradesListUsdFuturesResp.class);
    }

    @Override
    public Flux<SymbolPriceTickerUsdFuturesResp> symbolPriceTickerUsdFutures(SymbolPriceTickerUsdFuturesReq req) {
        return symbolPriceTickerUsdFuturesEndpoint.execFlux(req,SymbolPriceTickerUsdFuturesResp.class);
    }
}
