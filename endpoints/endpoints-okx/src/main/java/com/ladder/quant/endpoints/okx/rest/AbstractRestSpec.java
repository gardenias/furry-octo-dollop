package com.ladder.quant.endpoints.okx.rest;

import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.quant.endpoints.core.rest.HeadersProducer;

public abstract class AbstractRestSpec {

  protected final WebClient webClient;
  protected final HeadersProducer headersProducer;

  public AbstractRestSpec(WebClient webClient, HeadersProducer headersProducer) {
    this.webClient = webClient;
    this.headersProducer = headersProducer;
  }
}
