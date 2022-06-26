package com.p.quant.endpoints.okx.rest;

import com.p.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint;
import com.p.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesReq;
import com.p.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesResp;
import com.p.quant.endpoints.okx.rest.asset.AssetValuationEndpoint;
import com.p.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationReq;
import com.p.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationResp;
import com.p.quant.endpoints.okx.rest.asset.CurrenciesEndpoint;
import com.p.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesReq;
import com.p.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesResp;
import com.p.quant.endpoints.okx.rest.asset.DepositAddEndpoint;
import com.p.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddReq;
import com.p.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddResp;
import com.p.quant.endpoints.okx.rest.asset.DepositHisEndpoint;
import com.p.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisReq;
import com.p.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisResp;
import com.p.quant.endpoints.okx.rest.asset.TransferEndpoint;
import com.p.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferReq;
import com.p.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferResp;
import com.p.quant.endpoints.okx.rest.asset.TransferStateEndpoint;
import com.p.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateReq;
import com.p.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateResp;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelReq;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelResp;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalEndpoint;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalReq;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalResp;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisReq;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisResp;
import com.p.quant.endpoints.rest.HeadersProducer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OkxV5AssetRestSpecImpl extends AbstractRestSpec implements OkxV5AssetRestSpec {

    private final AssetBalancesEndpoint assetBalances;
    private final AssetValuationEndpoint assetValuation;
    private final CurrenciesEndpoint currencies;
    private final DepositAddEndpoint depositAdd;
    private final DepositHisEndpoint depositHis;
    private final TransferEndpoint transfer;
    private final TransferStateEndpoint transferState;
    private final WithdrawalCancelEndpoint withdrawalCancel;
    private final WithdrawalEndpoint withdrawal;
    private final WithdrawalHisEndpoint withdrawalHis;

    public OkxV5AssetRestSpecImpl(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer);
        this.assetBalances = new AssetBalancesEndpoint(webClient, headersProducer);
        this.assetValuation = new AssetValuationEndpoint(webClient, headersProducer);
        this.currencies = new CurrenciesEndpoint(webClient, headersProducer);
        this.depositAdd = new DepositAddEndpoint(webClient, headersProducer);
        this.depositHis = new DepositHisEndpoint(webClient, headersProducer);
        this.transfer = new TransferEndpoint(webClient, headersProducer);
        this.transferState = new TransferStateEndpoint(webClient, headersProducer);
        this.withdrawalCancel = new WithdrawalCancelEndpoint(webClient, headersProducer);
        this.withdrawal = new WithdrawalEndpoint(webClient, headersProducer);
        this.withdrawalHis = new WithdrawalHisEndpoint(webClient, headersProducer);
    }

    @Override
    public Mono<BalancesResp> assetBalances(BalancesReq req) {
        return assetBalances.exec(req, BalancesResp.class);
    }

    @Override
    public Mono<AssetValuationResp> assetValuation(AssetValuationReq req) {
        return assetValuation.exec(req, AssetValuationResp.class);
    }

    @Override
    public Mono<CurrenciesResp> currencies(CurrenciesReq req) {
        return currencies.exec(req, CurrenciesResp.class);
    }

    @Override
    public Mono<DepositAddResp> depositAdd(DepositAddReq req) {
        return depositAdd.exec(req, DepositAddResp.class);
    }

    @Override
    public Mono<DepositHisResp> depositHis(DepositHisReq req) {
        return depositHis.exec(req, DepositHisResp.class);
    }

    @Override
    public Mono<TransferResp> transfer(TransferReq req) {
        return transfer.exec(req, TransferResp.class);
    }

    @Override
    public Mono<TransferStateResp> transferState(TransferStateReq req) {
        return transferState.exec(req, TransferStateResp.class);
    }

    @Override
    public Mono<WithdrawalCancelResp> withdrawalCancel(WithdrawalCancelReq req) {
        return withdrawalCancel.exec(req, WithdrawalCancelResp.class);
    }

    @Override
    public Mono<WithdrawalResp> withdrawal(WithdrawalReq req) {
        return withdrawal.exec(req, WithdrawalResp.class);
    }

    @Override
    public Mono<WithdrawalHisResp> withdrawalHis(WithdrawalHisReq req) {
        return withdrawalHis.exec(req, WithdrawalHisResp.class);
    }
}
