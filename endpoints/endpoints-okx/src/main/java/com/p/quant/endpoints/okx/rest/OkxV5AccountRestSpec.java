package com.p.quant.endpoints.okx.rest;

import com.p.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceReq;
import com.p.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceResp;
import com.p.quant.endpoints.okx.rest.account.AccountPositionRiskEndpoint;
import com.p.quant.endpoints.okx.rest.account.AccountPositionsEndpoint;
import com.p.quant.endpoints.okx.rest.account.LeverageInfoEndpoint;
import com.p.quant.endpoints.okx.rest.account.MaxSizeEndpoint;
import com.p.quant.endpoints.okx.rest.account.SetLeverageEndpoint;
import com.p.quant.endpoints.okx.rest.account.SetPositionModeEndpoint;
import reactor.core.publisher.Mono;

public interface OkxV5AccountRestSpec {

    Mono<AccountBalanceResp> accountBalance(AccountBalanceReq req);

    Mono<AccountPositionRiskEndpoint.AccountPositionRiskResp> accountPositionRisk(AccountPositionRiskEndpoint.AccountPositionRiskReq req);

    Mono<AccountPositionsEndpoint.AccountPositionsResp> accountPositions(AccountPositionsEndpoint.AccountPositionsReq req);

    Mono<LeverageInfoEndpoint.LeverageInfoResp> leverageInfo(LeverageInfoEndpoint.LeverageInfoReq req);

    Mono<MaxSizeEndpoint.MaxSizeResp> maxSize(MaxSizeEndpoint.MaxSizeReq req);

    Mono<SetLeverageEndpoint.SetLeverageResp> setLeverage(SetLeverageEndpoint.SetLeverageReq req);

    Mono<SetPositionModeEndpoint.SetPositionModeResp> setPositionMode(SetPositionModeEndpoint.SetPositionModeReq req);

}
