package com.ladder.quant.endpoints.okx.rest;

import reactor.core.publisher.Mono;

import com.ladder.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceReq;
import com.ladder.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceResp;
import com.ladder.quant.endpoints.okx.rest.account.AccountPositionRiskEndpoint;
import com.ladder.quant.endpoints.okx.rest.account.AccountPositionsEndpoint;
import com.ladder.quant.endpoints.okx.rest.account.LeverageInfoEndpoint;
import com.ladder.quant.endpoints.okx.rest.account.MaxSizeEndpoint;
import com.ladder.quant.endpoints.okx.rest.account.SetLeverageEndpoint;
import com.ladder.quant.endpoints.okx.rest.account.SetPositionModeEndpoint;

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
