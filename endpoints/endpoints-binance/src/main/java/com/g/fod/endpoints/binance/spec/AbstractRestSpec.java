package com.g.fod.endpoints.binance.spec;

import com.g.fod.endpoints.binance.common.SignatureProducer;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstractRestSpec {

  protected final WebClient webClient;
  protected final SignatureProducer signatureProducer;

  public AbstractRestSpec(WebClient webClient, SignatureProducer signatureProducer) {
    this.webClient = webClient;
    this.signatureProducer = signatureProducer;
  }
}
