package com.p.quant.apps.gateway.rest;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.p.quant.endpoints.okx.OkxV5ApiSpec;
import com.p.quant.endpoints.okx.OkxV5WSSpec;
import com.p.quant.endpoints.okx.domain.Dict.BookType;
import com.p.quant.endpoints.okx.domain.Dict.CandleType;
import com.p.quant.endpoints.okx.domain.Dict.InstType;
import com.p.quant.endpoints.okx.domain.InstId;
import com.p.quant.endpoints.okx.rest.OkxV5AccountRestSpec;
import com.p.quant.endpoints.okx.rest.OkxV5AssetRestSpec;
import com.p.quant.endpoints.okx.rest.OkxV5MarketRestSpec;
import com.p.quant.endpoints.okx.rest.OkxV5PubRestSpec;
import com.p.quant.endpoints.okx.rest.OkxV5TradeRestSpec;
import com.p.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceReq;
import com.p.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceResp;
import com.p.quant.endpoints.okx.rest.account.AccountPositionRiskEndpoint.AccountPositionRiskReq;
import com.p.quant.endpoints.okx.rest.account.AccountPositionRiskEndpoint.AccountPositionRiskResp;
import com.p.quant.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsReq;
import com.p.quant.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsResp;
import com.p.quant.endpoints.okx.rest.account.LeverageInfoEndpoint.LeverageInfoReq;
import com.p.quant.endpoints.okx.rest.account.LeverageInfoEndpoint.LeverageInfoResp;
import com.p.quant.endpoints.okx.rest.account.MaxSizeEndpoint.MaxSizeReq;
import com.p.quant.endpoints.okx.rest.account.MaxSizeEndpoint.MaxSizeResp;
import com.p.quant.endpoints.okx.rest.account.SetLeverageEndpoint.SetLeverageReq;
import com.p.quant.endpoints.okx.rest.account.SetLeverageEndpoint.SetLeverageResp;
import com.p.quant.endpoints.okx.rest.account.SetPositionModeEndpoint.SetPositionModeReq;
import com.p.quant.endpoints.okx.rest.account.SetPositionModeEndpoint.SetPositionModeResp;
import com.p.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesReq;
import com.p.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesResp;
import com.p.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationReq;
import com.p.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationResp;
import com.p.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesReq;
import com.p.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesResp;
import com.p.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddReq;
import com.p.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddResp;
import com.p.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisReq;
import com.p.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisResp;
import com.p.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferReq;
import com.p.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferResp;
import com.p.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateReq;
import com.p.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateResp;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelReq;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelResp;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalReq;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalResp;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisReq;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisResp;
import com.p.quant.endpoints.okx.rest.market.BooksEndpoint.BooksReq;
import com.p.quant.endpoints.okx.rest.market.BooksEndpoint.BooksResp;
import com.p.quant.endpoints.okx.rest.market.CandlesEndpoint.CandlesReq;
import com.p.quant.endpoints.okx.rest.market.CandlesEndpoint.CandlesResp;
import com.p.quant.endpoints.okx.rest.market.TickerEndpoint.TickerReq;
import com.p.quant.endpoints.okx.rest.market.TickerEndpoint.TickerResp;
import com.p.quant.endpoints.okx.rest.market.TickersEndpoint.TickersReq;
import com.p.quant.endpoints.okx.rest.market.TickersEndpoint.TickersResp;
import com.p.quant.endpoints.okx.rest.pub.FundingRateEndpoint.FundingRateReq;
import com.p.quant.endpoints.okx.rest.pub.FundingRateEndpoint.FundingRateResp;
import com.p.quant.endpoints.okx.rest.pub.FundingRateHisEndpoint.FundingRateHisReq;
import com.p.quant.endpoints.okx.rest.pub.FundingRateHisEndpoint.FundingRateHisResp;
import com.p.quant.endpoints.okx.rest.pub.InstrumentsEndpoint.InstrumentsReq;
import com.p.quant.endpoints.okx.rest.pub.InstrumentsEndpoint.InstrumentsResp;
import com.p.quant.endpoints.okx.rest.pub.StatusEndpoint.StatusReq;
import com.p.quant.endpoints.okx.rest.pub.StatusEndpoint.StatusResp;
import com.p.quant.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderReq;
import com.p.quant.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderResp;
import com.p.quant.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderReq;
import com.p.quant.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderResp;
import com.p.quant.endpoints.okx.rest.trade.BatchCancelOrderEndpoint.BatchCancelReq;
import com.p.quant.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderReq;
import com.p.quant.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderResp;
import com.p.quant.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelReq;
import com.p.quant.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelResp;
import com.p.quant.endpoints.okx.rest.trade.ClosePositionEndpoint.ClosePositionReq;
import com.p.quant.endpoints.okx.rest.trade.ClosePositionEndpoint.ClosePositionResp;
import com.p.quant.endpoints.okx.rest.trade.FillsEndpoint.FillsReq;
import com.p.quant.endpoints.okx.rest.trade.FillsEndpoint.FillsResp;
import com.p.quant.endpoints.okx.rest.trade.FillsHistoryEndpoint.FillsHistoryReq;
import com.p.quant.endpoints.okx.rest.trade.FillsHistoryEndpoint.FillsHistoryResp;
import com.p.quant.endpoints.okx.rest.trade.GetOrderEndpoint.OrderReq;
import com.p.quant.endpoints.okx.rest.trade.GetOrderEndpoint.OrderResp;
import com.p.quant.endpoints.okx.rest.trade.OrderArchiveEndpoint.OrdArchiveReq;
import com.p.quant.endpoints.okx.rest.trade.OrderArchiveEndpoint.OrdArchiveResp;
import com.p.quant.endpoints.okx.rest.trade.OrderHisotryEndpoint.OrdHisReq;
import com.p.quant.endpoints.okx.rest.trade.OrderHisotryEndpoint.OrdHisResp;
import com.p.quant.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsReq;
import com.p.quant.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsResp;
import com.p.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderReq;
import com.p.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderResp;
import com.p.quant.endpoints.okx.ws.OkxV5WSPrivateSpec;
import com.p.quant.endpoints.okx.ws.OkxV5WSPublicSpec;
import com.p.quant.endpoints.okx.ws.args.AmendOrdArg;
import com.p.quant.endpoints.okx.ws.args.CancelOrdArg;
import com.p.quant.endpoints.okx.ws.args.PlaceOrdArg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/okx/perpetual")
public class PerpetualRestResources implements
    OkxV5AccountRestSpec, OkxV5AssetRestSpec, OkxV5MarketRestSpec,
    OkxV5PubRestSpec, OkxV5TradeRestSpec, OkxV5WSPublicSpec, OkxV5WSPrivateSpec {

    private final OkxV5ApiSpec restSpec;
    private final OkxV5WSSpec wsSpec;

    @Autowired
    public PerpetualRestResources(OkxV5ApiSpec restSpec, OkxV5WSSpec wsSpec) {
        this.restSpec = restSpec;
        this.wsSpec = wsSpec;
    }

//    @GetMapping("/instruments")
//    public Flux<Instrument> instruments() {
//        val respMono =
//            restApiSpec.instruments(new InstrumentsReq().setInstType(InstType.SWAP));
//        return respMono.flatMapMany(
//            (Function<InstrumentsResp, Publisher<Instrument>>) instrumentsResp -> Flux.fromIterable(
//                instrumentsResp.data()).map((Data ele) -> {
//                val instrument = new Instrument();
//                // TODO data -> instrument
//                return instrument;
//            }));
//    }
//
//    @GetMapping("/instruments/{id}")
//    public Mono<Instrument> instruments(@PathVariable String id) {
//        val respMono =
//            restApiSpec.instruments(new InstrumentsReq().setInstType(InstType.SWAP).setInstId(new InstId(id)));
//
//        return respMono.map(ele -> {return new Instrument();});
//    }

    @Override
    @PostMapping("/account-balance")
    public Mono<AccountBalanceResp> accountBalance(@RequestBody AccountBalanceReq req) {
        return restSpec.accountBalance(req);
    }

    @Override
    @PostMapping("/account-position-risk")
    public Mono<AccountPositionRiskResp> accountPositionRisk(@RequestBody AccountPositionRiskReq req) {
        return restSpec.accountPositionRisk(req);
    }

    @Override
    @PostMapping("/account-positions")
    public Mono<AccountPositionsResp> accountPositions(@RequestBody AccountPositionsReq req) {
        return restSpec.accountPositions(req);
    }

    @Override
    @PostMapping("/trade/leverage-info")
    public Mono<LeverageInfoResp> leverageInfo(@RequestBody LeverageInfoReq req) {
        return restSpec.leverageInfo(req);
    }

    @Override
    @PostMapping("/trade/max-size")
    public Mono<MaxSizeResp> maxSize(@RequestBody MaxSizeReq req) {
        return restSpec.maxSize(req);
    }

    @Override
    @PostMapping("/trade/set-leverage")
    public Mono<SetLeverageResp> setLeverage(@RequestBody SetLeverageReq req) {
        return restSpec.setLeverage(req);
    }

    @Override
    @PostMapping("/trade/set-position-mode")
    public Mono<SetPositionModeResp> setPositionMode(@RequestBody SetPositionModeReq req) {
        return restSpec.setPositionMode(req);
    }

    @Override
    @PostMapping("/asset-balances")
    public Mono<BalancesResp> assetBalances(@RequestBody BalancesReq req) {
        return restSpec.assetBalances(req);
    }

    @Override
    @PostMapping("/asset-valuation")
    public Mono<AssetValuationResp> assetValuation(@RequestBody AssetValuationReq req) {
        return restSpec.assetValuation(req);
    }

    @Override
    @PostMapping("//currencies")
    public Mono<CurrenciesResp> currencies(@RequestBody CurrenciesReq req) {
        return restSpec.currencies(req);
    }

    @Override
    @PostMapping("/deposit-add")
    public Mono<DepositAddResp> depositAdd(@RequestBody DepositAddReq req) {
        return restSpec.depositAdd(req);
    }

    @Override
    @PostMapping("/deposit-his")
    public Mono<DepositHisResp> depositHis(@RequestBody DepositHisReq req) {
        return restSpec.depositHis(req);
    }

    @Override
    @PostMapping("/transfer")
    public Mono<TransferResp> transfer(@RequestBody TransferReq req) {
        return restSpec.transfer(req);
    }

    @Override
    @PostMapping("/transfer-state")
    public Mono<TransferStateResp> transferState(@RequestBody TransferStateReq req) {
        return restSpec.transferState(req);
    }

    @Override
    @PostMapping("/withdrawal-cancel")
    public Mono<WithdrawalCancelResp> withdrawalCancel(@RequestBody WithdrawalCancelReq req) {
        return restSpec.withdrawalCancel(req);
    }

    @Override
    @PostMapping("/withdrawal")
    public Mono<WithdrawalResp> withdrawal(@RequestBody WithdrawalReq req) {
        return restSpec.withdrawal(req);
    }

    @Override
    @PostMapping("/withdrawal-his")
    public Mono<WithdrawalHisResp> withdrawalHis(@RequestBody WithdrawalHisReq req) {
        return restSpec.withdrawalHis(req);
    }

    @Override
    @PostMapping("/pub/books")
    public Mono<BooksResp> books(@RequestBody BooksReq req) {
        return restSpec.books(req);
    }

    @Override
    @PostMapping("/pub/candles")
    public Mono<CandlesResp> candles(@RequestBody CandlesReq req) {
        return restSpec.candles(req);
    }

    @Override
    @PostMapping("/pub/ticker")
    public Mono<TickerResp> ticker(@RequestBody TickerReq req) {
        return restSpec.ticker(req);
    }

    @Override
    @PostMapping("/pub/tickers")
    public Mono<TickersResp> tickers(@RequestBody TickersReq req) {
        return restSpec.tickers(req);
    }

    @Override
    @PostMapping("/pub/funding-rate")
    public Mono<FundingRateResp> fundingRate(@RequestBody FundingRateReq req) {
        return restSpec.fundingRate(req);
    }

    @Override
    @PostMapping("/pub/funding-rate-his")
    public Mono<FundingRateHisResp> fundingRateHis(@RequestBody FundingRateHisReq req) {
        return restSpec.fundingRateHis(req);
    }

    @Override
    @PostMapping("/pub/status")
    public Mono<StatusResp> status(@RequestBody StatusReq req) {
        return restSpec.status(req);
    }

    @Override
    @PostMapping("/pub/instruments")
    public Mono<InstrumentsResp> instruments(@RequestBody InstrumentsReq req) {
        return restSpec.instruments(req);
    }

    @Override
    @PostMapping("/trade/amend-order")
    public Mono<AmendOrderResp> amendOrder(@RequestBody AmendOrderReq req) {
        return restSpec.amendOrder(req);
    }

    @Override
    @PostMapping("/trade/batch-amend-order")
    public Mono<BatchAmendOrderResp> batchAmendOrder(@RequestBody BatchAmendOrderReq req) {
        return restSpec.batchAmendOrder(req);
    }

    @Override
    @PostMapping("/trade/batch-cancel-order")
    public Mono<CancelResp> batchCancelOrder(@RequestBody BatchCancelReq req) {
        return restSpec.batchCancelOrder(req);
    }

    @Override
    @PostMapping("/trade/batch-order")
    public Mono<BatchOrderResp> batchOrder(@RequestBody BatchOrderReq req) {
        return restSpec.batchOrder(req);
    }

    @Override
    @PostMapping("/trade/cancel-order")
    public Mono<CancelResp> cancelOrder(@RequestBody CancelReq req) {
        return restSpec.cancelOrder(req);
    }

    @Override
    @PostMapping("/trade/close-position")
    public Mono<ClosePositionResp> closePosition(@RequestBody ClosePositionReq req) {
        return restSpec.closePosition(req);
    }

    @Override
    @PostMapping("/trade/fills")
    public Mono<FillsResp> fills(@RequestBody FillsReq req) {
        return restSpec.fills(req);
    }

    @Override
    @PostMapping("/trade/fills-history")
    public Mono<FillsHistoryResp> fillsHistory(@RequestBody FillsHistoryReq req) {
        return restSpec.fillsHistory(req);
    }

    @Override
    @PostMapping("/trade/get-order")
    public Mono<OrderResp> getOrder(@RequestBody OrderReq req) {
        return restSpec.getOrder(req);
    }

    @Override
    @PostMapping("/trade/order-archive")
    public Mono<OrdArchiveResp> orderArchive(@RequestBody OrdArchiveReq req) {
        return restSpec.orderArchive(req);
    }

    @Override
    @PostMapping("/trade/order-history")
    public Mono<OrdHisResp> orderHistory(@RequestBody OrdHisReq req) {
        return restSpec.orderHistory(req);
    }

    @Override
    @PostMapping("/trade/pending-orders")
    public Mono<PendingOrdsResp> pendingOrders(@RequestBody PendingOrdsReq req) {
        return restSpec.pendingOrders(req);
    }

    @Override
    @PostMapping("/trade/place-order")
    public Mono<PlaceOrderResp> placeOrder(@RequestBody PlaceOrderReq req) {
        return restSpec.placeOrder(req);
    }

    @PostMapping("/ws/login")
    public void login(@RequestBody LoginReq req) {
        login(req.getKey(), req.getSec(), req.getPassphrase());
    }

    @Setter
    @Getter
    static class LoginReq implements Serializable {

        private String key;
        private String sec;
        private String passphrase;
    }

    @Override
    public void login(String key, String sec, String passphrase) {
        wsSpec.priv(key, sec, passphrase);
    }

    @Override
    @GetMapping("/ws/pri/account")
    public void account() {
        wsSpec.priv().account();

    }

    @Override
    @GetMapping("/ws/pri/account/{ccy}")
    public void account(@PathVariable String ccy) {
        wsSpec.priv().account(ccy);
    }

    @Override
    @GetMapping("/ws/pri/positions/{type}")
    public void positions(@PathVariable InstType type) {
        wsSpec.priv().positions(type);
    }

    @Override
    @GetMapping("/ws/pri/positions/{type}/{instId}")
    public void positions(@PathVariable InstType type, @PathVariable InstId instId) {
        wsSpec.priv().positions(type, instId);
    }

    @Override
    @GetMapping("/ws/pri/orders/{type}")
    public void orders(@PathVariable InstType type) {
        wsSpec.priv().orders(type);
    }

    @Override
    @GetMapping("/ws/pri/orders/{type}/{instId}")
    public void orders(@PathVariable InstType type, @PathVariable InstId instId) {
        wsSpec.priv().orders(type, instId);
    }

    @Override
    @GetMapping("/ws/pri/orders-algo/{type}")
    public void ordersAlgo(@PathVariable InstType type) {
        wsSpec.priv().ordersAlgo(type);
    }

    @Override
    @GetMapping("/ws/pri/orders-algo/{type}/{instId}")
    public void ordersAlgo(@NonNull InstType type, @PathVariable InstId instId) {
        wsSpec.priv().ordersAlgo(type, instId);
    }

    @Override
    @GetMapping("/ws/pri/algo-advance/{type}")
    public void algoAdvance(@PathVariable InstType type) {
        wsSpec.priv().algoAdvance(type);
    }

    @Override
    @GetMapping("/ws/pri/algo-advance/{type}/{instId}")
    public void algoAdvance(@PathVariable InstType type, @PathVariable InstId instId) {
        wsSpec.priv().algoAdvance(type, instId);
    }

    @Override
    @GetMapping("/ws/pri/liquidation-warning/{type}")
    public void liquidationWarning(@PathVariable InstType type) {
        wsSpec.priv().liquidationWarning(type);
    }

    @Override
    @GetMapping("/ws/pri/balance-and-position")
    public void balanceAndPosition() {
        wsSpec.priv().balanceAndPosition();
    }

    @Override
    public void submit(PlaceOrdArg... args) {
        wsSpec.priv().submit(args);
    }

    @Override
    public void cancel(@NonNull CancelOrdArg... args) {
        wsSpec.priv().cancel(args);
    }

    @Override
    public void amend(@NonNull AmendOrdArg... args) {
        wsSpec.priv().amend(args);

    }

    @Override
    @GetMapping("/ws/sub/sub-instruments/{instType}")
    public void subInstruments(@PathVariable InstType instType) {
        wsSpec.pub().subInstruments(instType);
    }

    @Override
    @GetMapping("/ws/sub/ticker/{instId}")
    public void ticker(@PathVariable InstId instId) {
        wsSpec.pub().ticker(instId);
    }

    @Override
    @GetMapping("/ws/sub/candle/{instId}/{type}")
    public void candle(@PathVariable InstId instId, @PathVariable CandleType type) {
        wsSpec.pub().candle(instId, type);

    }

    @Override
    @GetMapping("/ws/sub/trades/{instId}")
    public void trades(@PathVariable InstId instId) {
        wsSpec.pub().trades(instId);
    }

    @Override
    @GetMapping("/ws/sub/funding-rate/{instId}")
    public void fundingRate(@PathVariable InstId instId) {
        wsSpec.pub().fundingRate(instId);
    }

    @Override
    @GetMapping("/ws/sub/status")
    public void status() {
        wsSpec.pub().status();
    }

    @Override
    @GetMapping("/ws/sub/books/{instId}/{type}")
    public void books(@PathVariable InstId instId, @PathVariable BookType type) {
        wsSpec.pub().books(instId, type);
    }
}