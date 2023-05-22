package com.g.fod.endpoints.binance.spec;

import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import reactor.core.publisher.Mono;

public interface BinanceAccountRestSpec {

  Mono<AccountInfoResp> accountInfo(AccountInfoReq req);
}
