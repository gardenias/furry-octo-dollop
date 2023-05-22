package com.g.fod.endpoints.okx.rest;

import com.g.fod.endpoints.okx.rest.asset.*;
import reactor.core.publisher.Mono;

public interface OkxV5AssetRestSpec {

  Mono<AssetBalancesEndpoint.BalancesResp> assetBalances(AssetBalancesEndpoint.BalancesReq req);

  Mono<AssetValuationEndpoint.AssetValuationResp> assetValuation(AssetValuationEndpoint.AssetValuationReq req);

  Mono<CurrenciesEndpoint.CurrenciesResp> currencies(CurrenciesEndpoint.CurrenciesReq req);

  Mono<DepositAddEndpoint.DepositAddResp> depositAdd(DepositAddEndpoint.DepositAddReq req);

  Mono<DepositHisEndpoint.DepositHisResp> depositHis(DepositHisEndpoint.DepositHisReq req);

  Mono<TransferEndpoint.TransferResp> transfer(TransferEndpoint.TransferReq req);

  Mono<TransferStateEndpoint.TransferStateResp> transferState(TransferStateEndpoint.TransferStateReq req);

  Mono<WithdrawalCancelEndpoint.WithdrawalCancelResp> withdrawalCancel(WithdrawalCancelEndpoint.WithdrawalCancelReq req);

  Mono<WithdrawalEndpoint.WithdrawalResp> withdrawal(WithdrawalEndpoint.WithdrawalReq req);

  Mono<WithdrawalHisEndpoint.WithdrawalHisResp> withdrawalHis(WithdrawalHisEndpoint.WithdrawalHisReq req);

}
