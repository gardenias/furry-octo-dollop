package com.g.fod.endpoints.okx.rest;

import lombok.extern.slf4j.Slf4j;

import com.g.common.endpoints.core.rest.HeadersProducer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.g.fod.endpoints.okx.rest.asset.AssetBalancesEndpoint;
import com.g.fod.endpoints.okx.rest.asset.AssetValuationEndpoint;
import com.g.fod.endpoints.okx.rest.asset.CurrenciesEndpoint;
import com.g.fod.endpoints.okx.rest.asset.DepositAddEndpoint;
import com.g.fod.endpoints.okx.rest.asset.DepositHisEndpoint;
import com.g.fod.endpoints.okx.rest.asset.TransferEndpoint;
import com.g.fod.endpoints.okx.rest.asset.TransferStateEndpoint;
import com.g.fod.endpoints.okx.rest.asset.WithdrawalCancelEndpoint;
import com.g.fod.endpoints.okx.rest.asset.WithdrawalEndpoint;
import com.g.fod.endpoints.okx.rest.asset.WithdrawalHisEndpoint;

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
  public Mono<AssetBalancesEndpoint.BalancesResp> assetBalances(AssetBalancesEndpoint.BalancesReq req) {
    return assetBalances.exec(req, AssetBalancesEndpoint.BalancesResp.class);
  }

  @Override
  public Mono<AssetValuationEndpoint.AssetValuationResp> assetValuation(AssetValuationEndpoint.AssetValuationReq req) {
    return assetValuation.exec(req, AssetValuationEndpoint.AssetValuationResp.class);
  }

  @Override
  public Mono<CurrenciesEndpoint.CurrenciesResp> currencies(CurrenciesEndpoint.CurrenciesReq req) {
    return currencies.exec(req, CurrenciesEndpoint.CurrenciesResp.class);
  }

  @Override
  public Mono<DepositAddEndpoint.DepositAddResp> depositAdd(DepositAddEndpoint.DepositAddReq req) {
    return depositAdd.exec(req, DepositAddEndpoint.DepositAddResp.class);
  }

  @Override
  public Mono<DepositHisEndpoint.DepositHisResp> depositHis(DepositHisEndpoint.DepositHisReq req) {
    return depositHis.exec(req, DepositHisEndpoint.DepositHisResp.class);
  }

  @Override
  public Mono<TransferEndpoint.TransferResp> transfer(TransferEndpoint.TransferReq req) {
    return transfer.exec(req, TransferEndpoint.TransferResp.class);
  }

  @Override
  public Mono<TransferStateEndpoint.TransferStateResp> transferState(TransferStateEndpoint.TransferStateReq req) {
    return transferState.exec(req, TransferStateEndpoint.TransferStateResp.class);
  }

  @Override
  public Mono<WithdrawalCancelEndpoint.WithdrawalCancelResp> withdrawalCancel(WithdrawalCancelEndpoint.WithdrawalCancelReq req) {
    return withdrawalCancel.exec(req, WithdrawalCancelEndpoint.WithdrawalCancelResp.class);
  }

  @Override
  public Mono<WithdrawalEndpoint.WithdrawalResp> withdrawal(WithdrawalEndpoint.WithdrawalReq req) {
    return withdrawal.exec(req, WithdrawalEndpoint.WithdrawalResp.class);
  }

  @Override
  public Mono<WithdrawalHisEndpoint.WithdrawalHisResp> withdrawalHis(WithdrawalHisEndpoint.WithdrawalHisReq req) {
    return withdrawalHis.exec(req, WithdrawalHisEndpoint.WithdrawalHisResp.class);
  }
}
