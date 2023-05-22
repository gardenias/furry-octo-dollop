package com.g.fod.endpoints.binance.spec;

import com.g.fod.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderReq;
import com.g.fod.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderResp;
import com.g.fod.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderReq;
import com.g.fod.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderResp;
import com.g.fod.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderReq;
import com.g.fod.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderResp;
import com.g.fod.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderReq;
import com.g.fod.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderResp;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderReq;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderResp;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersReq;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersResp;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderReq;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderResp;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderReq;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderResp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BinanceTradeRestSpec {

  Mono<PlaceSpotOrderResp> placeSpotOrder(PlaceSpotOrderReq req);

  Mono<CancelSpotOrderResp> cancelSpotOrder(CancelSpotOrderReq req);

  Mono<PlaceUsdFuturesOrderResp> placeUsdFuturesOrder(PlaceUsdFuturesOrderReq req);

  Flux<PlaceMultipleUsdFuturesOrdersResp> placeMultipleUsdFuturesOrders(PlaceMultipleUsdFuturesOrdersReq req);

  Mono<PlaceMarginAccountOrderResp> placeMarginAccountOrder(PlaceMarginAccountOrderReq req);

  Mono<QueryUsdFuturesOrderResp> queryUsdFuturesOrder(QueryUsdFuturesOrderReq req);

  Mono<CancelUseFuturesOrderResp> cancelUsdFuturesOrder(CancelUseFuturesOrderReq req);

  Mono<QuerySpotOrderResp> querySpotOrder(QuerySpotOrderReq req);
}

