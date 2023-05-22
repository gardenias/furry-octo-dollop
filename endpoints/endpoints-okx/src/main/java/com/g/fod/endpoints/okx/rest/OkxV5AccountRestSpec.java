package com.g.fod.endpoints.okx.rest;

import reactor.core.publisher.Mono;

import com.g.fod.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceReq;
import com.g.fod.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceResp;
import com.g.fod.endpoints.okx.rest.account.AccountPositionRiskEndpoint;
import com.g.fod.endpoints.okx.rest.account.AccountPositionsEndpoint;
import com.g.fod.endpoints.okx.rest.account.LeverageInfoEndpoint;
import com.g.fod.endpoints.okx.rest.account.MaxSizeEndpoint;
import com.g.fod.endpoints.okx.rest.account.SetLeverageEndpoint;
import com.g.fod.endpoints.okx.rest.account.SetPositionModeEndpoint;

public interface OkxV5AccountRestSpec {

  Mono<AccountBalanceResp> accountBalance(AccountBalanceReq req);

  Mono<AccountPositionRiskEndpoint.AccountPositionRiskResp> accountPositionRisk(
    AccountPositionRiskEndpoint.AccountPositionRiskReq req);

  Mono<AccountPositionsEndpoint.AccountPositionsResp> accountPositions(
    AccountPositionsEndpoint.AccountPositionsReq req);

  Mono<LeverageInfoEndpoint.LeverageInfoResp> leverageInfo(LeverageInfoEndpoint.LeverageInfoReq req);

  Mono<MaxSizeEndpoint.MaxSizeResp> maxSize(MaxSizeEndpoint.MaxSizeReq req);

  Mono<SetLeverageEndpoint.SetLeverageResp> setLeverage(SetLeverageEndpoint.SetLeverageReq req);

  Mono<SetPositionModeEndpoint.SetPositionModeResp> setPositionMode(SetPositionModeEndpoint.SetPositionModeReq req);

}
