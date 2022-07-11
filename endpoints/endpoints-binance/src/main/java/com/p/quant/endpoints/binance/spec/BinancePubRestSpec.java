package com.p.quant.endpoints.binance.spec;

import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import reactor.core.publisher.Mono;

public interface BinancePubRestSpec {

    Mono<AccountInfoResp> accountInfo(AccountInfoReq req);
}
