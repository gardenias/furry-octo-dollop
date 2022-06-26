package com.p.quant.endpoints.okx.rest;

import com.p.quant.endpoints.okx.rest.account.AccountBalanceEndpoint;
import com.p.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceReq;
import com.p.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceResp;
import com.p.quant.endpoints.okx.rest.account.AccountPositionRiskEndpoint;
import com.p.quant.endpoints.okx.rest.account.AccountPositionsEndpoint;
import com.p.quant.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsReq;
import com.p.quant.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsResp;
import com.p.quant.endpoints.okx.rest.account.LeverageInfoEndpoint;
import com.p.quant.endpoints.okx.rest.account.LeverageInfoEndpoint.LeverageInfoReq;
import com.p.quant.endpoints.okx.rest.account.LeverageInfoEndpoint.LeverageInfoResp;
import com.p.quant.endpoints.okx.rest.account.MaxSizeEndpoint;
import com.p.quant.endpoints.okx.rest.account.MaxSizeEndpoint.MaxSizeReq;
import com.p.quant.endpoints.okx.rest.account.MaxSizeEndpoint.MaxSizeResp;
import com.p.quant.endpoints.okx.rest.account.SetLeverageEndpoint;
import com.p.quant.endpoints.okx.rest.account.SetLeverageEndpoint.SetLeverageReq;
import com.p.quant.endpoints.okx.rest.account.SetLeverageEndpoint.SetLeverageResp;
import com.p.quant.endpoints.okx.rest.account.SetPositionModeEndpoint;
import com.p.quant.endpoints.okx.rest.account.SetPositionModeEndpoint.SetPositionModeReq;
import com.p.quant.endpoints.okx.rest.account.SetPositionModeEndpoint.SetPositionModeResp;
import com.p.quant.endpoints.rest.HeadersProducer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OkxV5AccountRestSpecImpl extends AbstractRestSpec implements OkxV5AccountRestSpec {

    private final AccountBalanceEndpoint accountBalanceEndpoint;
    private final AccountPositionRiskEndpoint accountPositionRiskEndpoint;
    private final AccountPositionsEndpoint accountPositionsEndpoint;
    private final LeverageInfoEndpoint leverageInfoEndpoint;
    private final MaxSizeEndpoint maxSizeEndpoint;
    private final SetLeverageEndpoint setLeverageEndpoint;
    private final SetPositionModeEndpoint setPositionModeEndpoint;

    public OkxV5AccountRestSpecImpl(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer);

        this.accountBalanceEndpoint = new AccountBalanceEndpoint(webClient, headersProducer);
        this.accountPositionRiskEndpoint = new AccountPositionRiskEndpoint(webClient, headersProducer);
        this.accountPositionsEndpoint = new AccountPositionsEndpoint(webClient, headersProducer);
        this.leverageInfoEndpoint = new LeverageInfoEndpoint(webClient, headersProducer);
        this.maxSizeEndpoint = new MaxSizeEndpoint(webClient, headersProducer);
        this.setLeverageEndpoint = new SetLeverageEndpoint(webClient, headersProducer);
        this.setPositionModeEndpoint = new SetPositionModeEndpoint(webClient, headersProducer);
    }

    public Mono<AccountBalanceResp> accountBalance(AccountBalanceReq req) {
        return accountBalanceEndpoint.exec(req, AccountBalanceResp.class);
    }

    public Mono<AccountPositionRiskEndpoint.AccountPositionRiskResp> accountPositionRisk(AccountPositionRiskEndpoint.AccountPositionRiskReq req) {
        return accountPositionRiskEndpoint.exec(req, AccountPositionRiskEndpoint.AccountPositionRiskResp.class);
    }

    public Mono<AccountPositionsResp> accountPositions(AccountPositionsReq req) {
        return accountPositionsEndpoint.exec(req, AccountPositionsResp.class);
    }

    public Mono<LeverageInfoResp> leverageInfo(LeverageInfoReq req) {
        return leverageInfoEndpoint.exec(req, LeverageInfoResp.class);
    }

    public Mono<MaxSizeResp> maxSize(MaxSizeReq req) {
        return maxSizeEndpoint.exec(req, MaxSizeResp.class);
    }

    public Mono<SetLeverageResp> setLeverage(SetLeverageReq req) {
        return setLeverageEndpoint.exec(req, SetLeverageResp.class);
    }

    public Mono<SetPositionModeResp> setPositionMode(SetPositionModeReq req) {
        return setPositionModeEndpoint.exec(req, SetPositionModeResp.class);
    }
}
