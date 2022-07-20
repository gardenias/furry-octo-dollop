package com.ladder.quant.endpoints.okx.rest;

import reactor.core.publisher.Mono;

import com.ladder.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesReq;
import com.ladder.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesResp;
import com.ladder.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationReq;
import com.ladder.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationResp;
import com.ladder.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesReq;
import com.ladder.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesResp;
import com.ladder.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddReq;
import com.ladder.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddResp;
import com.ladder.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisReq;
import com.ladder.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisResp;
import com.ladder.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferReq;
import com.ladder.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferResp;
import com.ladder.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateReq;
import com.ladder.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateResp;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelReq;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelResp;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalReq;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalResp;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisReq;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisResp;

public interface OkxV5AssetRestSpec {

  Mono<BalancesResp> assetBalances(BalancesReq req);

  Mono<AssetValuationResp> assetValuation(AssetValuationReq req);

  Mono<CurrenciesResp> currencies(CurrenciesReq req);

  Mono<DepositAddResp> depositAdd(DepositAddReq req);

  Mono<DepositHisResp> depositHis(DepositHisReq req);

  Mono<TransferResp> transfer(TransferReq req);

  Mono<TransferStateResp> transferState(TransferStateReq req);

  Mono<WithdrawalCancelResp> withdrawalCancel(WithdrawalCancelReq req);

  Mono<WithdrawalResp> withdrawal(WithdrawalReq req);

  Mono<WithdrawalHisResp> withdrawalHis(WithdrawalHisReq req);

}
