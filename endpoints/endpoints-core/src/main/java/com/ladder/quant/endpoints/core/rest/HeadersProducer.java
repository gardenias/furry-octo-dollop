package com.ladder.quant.endpoints.core.rest;

import java.util.function.Consumer;

import org.springframework.http.HttpHeaders;

public interface HeadersProducer {

    HeadersProducer NON_OP_HEADERS_PRODUCER = new NonOpHeadersProducer();

    Consumer<HttpHeaders> produce(Req req);
}
