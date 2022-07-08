package com.p.quant.endpoints.binance.spec;

import com.p.quant.endpoints.binance.rest1.market.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.p.quant.endpoints.binance.rest1.market.OldTradeLookupEndpoint.OldTradeLookupResp;
import com.p.quant.endpoints.binance.rest1.market.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest1.market.RecentTradesListEndpoint.RecentTradesListResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BinanceMarketRestSpec {

    Flux<OldTradeLookupResp> oldTradeLookup(OldTradeLookupReq req);
    
    Flux<RecentTradesListResp> recentTradesList(RecentTradesListReq req);
}
