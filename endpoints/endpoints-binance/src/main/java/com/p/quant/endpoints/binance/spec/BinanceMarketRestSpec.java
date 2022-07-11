package com.p.quant.endpoints.binance.spec;

import com.p.quant.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesReq;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;
import com.p.quant.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookReq;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BinanceMarketRestSpec {

    Flux<OldTradeLookupResp> oldTradeLookupSpot(OldTradeLookupReq req);
    
    Flux<RecentTradesListResp> recentTradesListSpot(RecentTradesListReq req);
    Mono<String> klinesSpot(KlinesReq req);

    Mono<OrderBookResp> orderBookSpot(OrderBookReq req);

    Flux<SymbolPriceTickerResp> symbolPriceTickerSpot(SymbolPriceTickerReq req);
    
    Mono<OrderBookUsdFuturesResp> orderBookUsdFutures(OrderBookUsdFuturesReq req);

    Flux<RecentTradesListUsdFuturesResp> recentTradesListUsdFutures(RecentTradesListUsdFuturesReq req);

    Flux<SymbolPriceTickerUsdFuturesResp> symbolPriceTickerUsdFutures(SymbolPriceTickerUsdFuturesReq req);
}
