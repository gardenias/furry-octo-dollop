package com.p.quant.endpoints.binance.spec.impl;

import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import com.p.quant.endpoints.binance.spec.AbstractRestSpec;
import com.p.quant.endpoints.binance.spec.BinanceAccountRestSpec;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BinancePubRestSpecImpl extends AbstractRestSpec implements BinanceAccountRestSpec {

    private final AccountInfoEndpoint accountInfoEndpoint;

    public BinancePubRestSpecImpl(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient,signatureProducer);
        this.accountInfoEndpoint = new AccountInfoEndpoint(webClient,signatureProducer);

    }
    @Override
    public Mono<AccountInfoResp> accountInfo(AccountInfoReq req) {
        return accountInfoEndpoint.exec(req, AccountInfoResp.class);
        
    }

}
