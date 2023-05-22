package com.g.fod.endpoints.okx.rest;

import reactor.core.publisher.Mono;

import com.g.fod.endpoints.okx.rest.market.BooksEndpoint;
import com.g.fod.endpoints.okx.rest.market.BooksEndpoint.BooksResp;
import com.g.fod.endpoints.okx.rest.market.CandlesEndpoint;
import com.g.fod.endpoints.okx.rest.market.TickerEndpoint;
import com.g.fod.endpoints.okx.rest.market.TickersEndpoint;

public interface OkxV5MarketRestSpec {

  Mono<BooksResp> books(BooksEndpoint.BooksReq req);

  Mono<CandlesEndpoint.CandlesResp> candles(CandlesEndpoint.CandlesReq req);

  Mono<TickerEndpoint.TickerResp> ticker(TickerEndpoint.TickerReq req);

  Mono<TickersEndpoint.TickersResp> tickers(TickersEndpoint.TickersReq req);
}
