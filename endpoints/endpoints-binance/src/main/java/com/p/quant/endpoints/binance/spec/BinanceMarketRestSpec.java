package com.p.quant.endpoints.binance.spec;

import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import reactor.core.publisher.Flux;

public interface BinanceMarketRestSpec {

    Flux<OldTradeLookupResp> oldTradeLookup(OldTradeLookupReq req);
    
    Flux<RecentTradesListResp> recentTradesList(RecentTradesListReq req);
}
