package com.p.quant.endpoints.okx.rest;

import com.p.quant.endpoints.okx.rest.market.BooksEndpoint;
import com.p.quant.endpoints.okx.rest.market.BooksEndpoint.BooksResp;
import com.p.quant.endpoints.okx.rest.market.CandlesEndpoint;
import com.p.quant.endpoints.okx.rest.market.TickerEndpoint;
import com.p.quant.endpoints.okx.rest.market.TickersEndpoint;
import reactor.core.publisher.Mono;

public interface OkxV5MarketRestSpec {

    Mono<BooksResp> books(BooksEndpoint.BooksReq req);

    Mono<CandlesEndpoint.CandlesResp> candles(CandlesEndpoint.CandlesReq req);

    Mono<TickerEndpoint.TickerResp> ticker(TickerEndpoint.TickerReq req);

    Mono<TickersEndpoint.TickersResp> tickers(TickersEndpoint.TickersReq req);
}
