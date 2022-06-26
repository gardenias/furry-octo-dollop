package com.p.quant.endpoints.okx.rest;

import com.p.quant.endpoints.okx.rest.market.BooksEndpoint;
import com.p.quant.endpoints.okx.rest.market.CandlesEndpoint;
import com.p.quant.endpoints.okx.rest.market.TickerEndpoint;
import com.p.quant.endpoints.okx.rest.market.TickersEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OkxV5MarketRestSpecImpl extends AbstractRestSpec implements OkxV5MarketRestSpec {

    private final BooksEndpoint booksEndpoint;
    private final CandlesEndpoint candlesEndpoint;
    private final TickerEndpoint tickerEndpoint;
    private final TickersEndpoint tickersEndpoint;

    public OkxV5MarketRestSpecImpl(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer);

        this.booksEndpoint = new BooksEndpoint(webClient, headersProducer);
        this.candlesEndpoint = new CandlesEndpoint(webClient, headersProducer);
        this.tickerEndpoint = new TickerEndpoint(webClient, headersProducer);
        this.tickersEndpoint = new TickersEndpoint(webClient, headersProducer);
    }

    @Override
    public Mono<BooksEndpoint.BooksResp> books(BooksEndpoint.BooksReq req) {
        return booksEndpoint.exec(req, BooksEndpoint.BooksResp.class);
    }

    @Override
    public Mono<CandlesEndpoint.CandlesResp> candles(CandlesEndpoint.CandlesReq req) {
        return candlesEndpoint.exec(req, CandlesEndpoint.CandlesResp.class);
    }

    @Override
    public Mono<TickerEndpoint.TickerResp> ticker(TickerEndpoint.TickerReq req) {
        return tickerEndpoint.exec(req, TickerEndpoint.TickerResp.class);
    }

    @Override
    public Mono<TickersEndpoint.TickersResp> tickers(TickersEndpoint.TickersReq req) {
        return tickersEndpoint.exec(req, TickersEndpoint.TickersResp.class);
    }
}
