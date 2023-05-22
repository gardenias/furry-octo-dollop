package com.g.fod.endpoints.binance.spec;

import com.g.fod.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesReq;
import com.g.fod.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.g.fod.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookReq;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookResp;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerReq;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesResp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesResp;
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
