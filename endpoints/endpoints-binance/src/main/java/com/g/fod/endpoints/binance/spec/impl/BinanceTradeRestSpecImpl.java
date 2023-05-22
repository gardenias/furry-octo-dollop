package com.g.fod.endpoints.binance.spec.impl;

import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceMultipleUsdFuturesOrdersEndpoint;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint;

import lombok.extern.slf4j.Slf4j;

import com.g.fod.endpoints.binance.spec.AbstractRestSpec;
import com.g.fod.endpoints.binance.spec.BinanceTradeRestSpec;
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
    super(webClient, signatureProducer);
    this.placeSpotOrderEndpoint = new PlaceSpotOrderEndpoint(webClient, signatureProducer);
    this.cancelSpotOrderEndpoint = new CancelSpotOrderEndpoint(webClient, signatureProducer);
    this.placeUsdFuturesOrderEndpoint = new PlaceUsdFuturesOrderEndpoint(webClient, signatureProducer);
    this.placeMultipleUsdFuturesOrdersEndpoint = new PlaceMultipleUsdFuturesOrdersEndpoint(webClient,
      signatureProducer);
    this.placeMarginAccountOrderEndpoint = new PlaceMarginAccountOrderEndpoint(webClient, signatureProducer);
    this.queryUsdFuturesOrderEndpoint = new QueryUsdFuturesOrderEndpoint(webClient, signatureProducer);
    this.cancelUseFuturesOrderEndpoint = new CancelUseFuturesOrderEndpoint(webClient, signatureProducer);
    this.querySpotOrderEndpoint = new QuerySpotOrderEndpoint(webClient, signatureProducer);

  }

  @Override
  public Mono<PlaceSpotOrderEndpoint.PlaceSpotOrderResp> placeSpotOrder(PlaceSpotOrderEndpoint.PlaceSpotOrderReq req) {
    return placeSpotOrderEndpoint.exec(req, PlaceSpotOrderEndpoint.PlaceSpotOrderResp.class);
  }

  @Override
  public Mono<CancelSpotOrderEndpoint.CancelSpotOrderResp> cancelSpotOrder(CancelSpotOrderEndpoint.CancelSpotOrderReq req) {
    return cancelSpotOrderEndpoint.exec(req, CancelSpotOrderEndpoint.CancelSpotOrderResp.class);
  }

  @Override
  public Mono<PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderResp> placeUsdFuturesOrder(PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderReq req) {
    return placeUsdFuturesOrderEndpoint.exec(req, PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderResp.class);
  }

  @Override
  public Flux<PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersResp> placeMultipleUsdFuturesOrders(PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersReq req) {
    return placeMultipleUsdFuturesOrdersEndpoint.execFlux(req, PlaceMultipleUsdFuturesOrdersEndpoint.PlaceMultipleUsdFuturesOrdersResp.class);
  }

  @Override
  public Mono<QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderResp> queryUsdFuturesOrder(QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderReq req) {
    return queryUsdFuturesOrderEndpoint.exec(req, QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderResp.class);
  }

  @Override
  public Mono<PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderResp> placeMarginAccountOrder(PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderReq req) {
    return placeMarginAccountOrderEndpoint.exec(req, PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderResp.class);
  }

  @Override
  public Mono<CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderResp> cancelUsdFuturesOrder(CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderReq req) {
    return cancelUseFuturesOrderEndpoint.exec(req, CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderResp.class);
  }

  @Override
  public Mono<QuerySpotOrderEndpoint.QuerySpotOrderResp> querySpotOrder(QuerySpotOrderEndpoint.QuerySpotOrderReq req) {
    return querySpotOrderEndpoint.exec(req, QuerySpotOrderEndpoint.QuerySpotOrderResp.class);
  }
}
