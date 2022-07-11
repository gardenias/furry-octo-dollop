package com.ladder.quant.endpoints.core.rest;

import java.util.function.Consumer;

import org.springframework.http.HttpHeaders;

public class NonOpHeadersProducer implements HeadersProducer {

    @Override
    public Consumer<HttpHeaders> produce(Req req) {
        return httpHeaders -> {
            // nothing need to do
        };
    }
}
