package com.p.quant.endpoints.binance.spec.impl;

import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint;
import com.p.quant.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderReq;
import com.p.quant.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderResp;
import com.p.quant.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint;
import com.p.quant.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderReq;
import com.p.quant.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderResp;
import com.p.quant.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint;
import com.p.quant.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderReq;
import com.p.quant.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderResp;
import com.p.quant.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint;
import com.p.quant.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderReq;
import com.p.quant.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderResp;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderResp;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersResp;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderResp;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderReq;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderResp;
import com.p.quant.endpoints.binance.spec.AbstractRestSpec;

import com.p.quant.endpoints.binance.spec.BinanceTradeRestSpec;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BinanceTradeRestSpecImpl extends AbstractRestSpec implements BinanceTradeRestSpec {

    private final PlaceSpotOrderEndpoint placeSpotOrderEndpoint;
    private final CancelSpotOrderEndpoint cancelSpotOrderEndpoint;
    private final QuerySpotOrderEndpoint querySpotOrderEndpoint;

    private final PlaceUsdFuturesOrderEndpoint placeUsdFuturesOrderEndpoint;
    private final PlaceMultipleUsdFuturesOrdersEndpoint placeMultipleUsdFuturesOrdersEndpoint;
    private final PlaceMarginAccountOrderEndpoint placeMarginAccountOrderEndpoint;
    private final QueryUsdFuturesOrderEndpoint queryUsdFuturesOrderEndpoint;
    private final CancelUseFuturesOrderEndpoint cancelUseFuturesOrderEndpoint;
    public BinanceTradeRestSpecImpl(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient,signatureProducer);
        this.placeSpotOrderEndpoint = new PlaceSpotOrderEndpoint(webClient,signatureProducer);
        this.cancelSpotOrderEndpoint = new CancelSpotOrderEndpoint(webClient,signatureProducer);
        this.placeUsdFuturesOrderEndpoint = new PlaceUsdFuturesOrderEndpoint(webClient,signatureProducer);
        this.placeMultipleUsdFuturesOrdersEndpoint = new PlaceMultipleUsdFuturesOrdersEndpoint(webClient,signatureProducer);
        this.placeMarginAccountOrderEndpoint = new PlaceMarginAccountOrderEndpoint(webClient,signatureProducer);
        this.queryUsdFuturesOrderEndpoint = new QueryUsdFuturesOrderEndpoint(webClient,signatureProducer);
        this.cancelUseFuturesOrderEndpoint = new CancelUseFuturesOrderEndpoint(webClient,signatureProducer);
        this.querySpotOrderEndpoint = new QuerySpotOrderEndpoint(webClient,signatureProducer);

    }

    @Override
    public Mono<PlaceSpotOrderResp> placeSpotOrder(PlaceSpotOrderReq req) {
        return placeSpotOrderEndpoint.exec(req, PlaceSpotOrderResp.class);
    }
    @Override
    public Mono<CancelSpotOrderResp> cancelSpotOrder(CancelSpotOrderReq req) {
        return cancelSpotOrderEndpoint.exec(req, CancelSpotOrderResp.class);
    }

    @Override
    public Mono<PlaceUsdFuturesOrderResp> placeUsdFuturesOrder(PlaceUsdFuturesOrderReq req) {
        return placeUsdFuturesOrderEndpoint.exec(req, PlaceUsdFuturesOrderResp.class);
    }

    @Override
    public Flux<PlaceMultipleUsdFuturesOrdersResp> placeMultipleUsdFuturesOrders(PlaceMultipleUsdFuturesOrdersReq req) {
        return placeMultipleUsdFuturesOrdersEndpoint.execFlux(req, PlaceMultipleUsdFuturesOrdersResp.class);
    }
    

    @Override
    public Mono<QueryUsdFuturesOrderResp> queryUsdFuturesOrder(QueryUsdFuturesOrderReq req) {
        return queryUsdFuturesOrderEndpoint.exec(req, QueryUsdFuturesOrderResp.class);
    }



    @Override
    public Mono<PlaceMarginAccountOrderResp> placeMarginAccountOrder(PlaceMarginAccountOrderReq req) {
        return placeMarginAccountOrderEndpoint.exec(req,PlaceMarginAccountOrderResp.class);
    }

    @Override
    public Mono<CancelUseFuturesOrderResp> cancelUsdFuturesOrder(CancelUseFuturesOrderReq req) {
        return cancelUseFuturesOrderEndpoint.exec(req,CancelUseFuturesOrderResp.class);
    }

    @Override
    public Mono<QuerySpotOrderResp> querySpotOrder(QuerySpotOrderReq req) {
        return querySpotOrderEndpoint.exec(req,QuerySpotOrderResp.class);
    }
}
