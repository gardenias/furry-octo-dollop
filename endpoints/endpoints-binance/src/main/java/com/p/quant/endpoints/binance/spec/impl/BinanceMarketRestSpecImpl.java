package com.p.quant.endpoints.binance.spec.impl;

import com.google.common.collect.Maps;

import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;

import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import com.p.quant.endpoints.binance.spec.AbstractRestSpec;
import com.p.quant.endpoints.binance.spec.BinanceMarketRestSpec;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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
