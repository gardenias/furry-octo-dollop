package com.g.fod.endpoints.binance.spec.impl;

import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.extern.slf4j.Slf4j;

import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import com.g.fod.endpoints.binance.spec.AbstractRestSpec;
import com.g.fod.endpoints.binance.spec.BinanceAccountRestSpec;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BinanceAccountRestSpecImpl extends AbstractRestSpec implements BinanceAccountRestSpec {

  private final AccountInfoEndpoint accountInfoEndpoint;

  public BinanceAccountRestSpecImpl(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer);
    this.accountInfoEndpoint = new AccountInfoEndpoint(webClient, signatureProducer);

  }

  @Override
  public Mono<AccountInfoResp> accountInfo(AccountInfoReq req) {
    return accountInfoEndpoint.exec(req, AccountInfoResp.class);

  }

}
