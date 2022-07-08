package com.p.quant.endpoints.binance.spec;

import com.p.quant.endpoints.binance.rest1.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.p.quant.endpoints.binance.rest1.account.spot.AccountInfoEndpoint.AccountInfoResp;
import reactor.core.publisher.Mono;

public interface BinanceAccountRestSpec {

    Mono<AccountInfoResp> accountInfo(AccountInfoReq req);
}
