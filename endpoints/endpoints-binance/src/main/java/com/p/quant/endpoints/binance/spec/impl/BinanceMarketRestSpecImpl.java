package com.p.quant.endpoints.binance.spec.impl;

import com.g.common.endpoints.core.rest.HeadersProducer;

import com.google.common.collect.Maps;

import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest1.market.OldTradeLookupEndpoint;
import com.p.quant.endpoints.binance.rest1.market.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.p.quant.endpoints.binance.rest1.market.OldTradeLookupEndpoint.OldTradeLookupResp;

import com.p.quant.endpoints.binance.rest1.market.RecentTradesListEndpoint;
import com.p.quant.endpoints.binance.rest1.market.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest1.market.RecentTradesListEndpoint.RecentTradesListResp;
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

    public BinanceMarketRestSpecImpl(WebClient webClient,  SignatureProducer signatureProducer) {
        super(webClient, signatureProducer);
        this.oldTradeLookupEndpoint = new OldTradeLookupEndpoint(webClient,signatureProducer);
        this.recentTradesListEndpoint = new RecentTradesListEndpoint(webClient,signatureProducer);

    }

    @Override
    public Flux<OldTradeLookupResp> oldTradeLookup(OldTradeLookupReq req) {
        return oldTradeLookupEndpoint.execGetFlux(req, OldTradeLookupResp.class,req);
    }

    @Override
    public Flux<RecentTradesListResp> recentTradesList(RecentTradesListReq req) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("symbol", "BTCUSDT");
        return recentTradesListEndpoint.execGetFlux(req, RecentTradesListResp.class,map);
    }
}
