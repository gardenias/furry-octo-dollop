package com.ladder.quant.endpoints.okx.rest;

import com.g.common.endpoints.core.rest.HeadersProducer;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstractRestSpec {

    protected final WebClient webClient;
    protected final HeadersProducer headersProducer;

    public AbstractRestSpec(WebClient webClient, HeadersProducer headersProducer) {
        this.webClient = webClient;
        this.headersProducer = headersProducer;
    }
}
