package com.ladder.quant.endpoints.okx.rest;

import com.ladder.quant.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderResp;
import com.ladder.quant.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderResp;
import com.ladder.quant.endpoints.okx.rest.trade.BatchCancelOrderEndpoint.BatchCancelReq;
import com.ladder.quant.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderResp;
import com.ladder.quant.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelReq;
import com.ladder.quant.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelResp;
import com.ladder.quant.endpoints.okx.rest.trade.ClosePositionEndpoint.ClosePositionReq;
import com.ladder.quant.endpoints.okx.rest.trade.ClosePositionEndpoint.ClosePositionResp;
import com.ladder.quant.endpoints.okx.rest.trade.FillsEndpoint.FillsReq;
import com.ladder.quant.endpoints.okx.rest.trade.FillsEndpoint.FillsResp;
import com.ladder.quant.endpoints.okx.rest.trade.FillsHistoryEndpoint.FillsHistoryReq;
import com.ladder.quant.endpoints.okx.rest.trade.FillsHistoryEndpoint.FillsHistoryResp;
import com.ladder.quant.endpoints.okx.rest.trade.GetOrderEndpoint.OrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.GetOrderEndpoint.OrderResp;
import com.ladder.quant.endpoints.okx.rest.trade.OrderArchiveEndpoint.OrdArchiveReq;
import com.ladder.quant.endpoints.okx.rest.trade.OrderArchiveEndpoint.OrdArchiveResp;
import com.ladder.quant.endpoints.okx.rest.trade.OrderHisotryEndpoint.OrdHisReq;
import com.ladder.quant.endpoints.okx.rest.trade.OrderHisotryEndpoint.OrdHisResp;
import com.ladder.quant.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsReq;
import com.ladder.quant.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsResp;
import com.ladder.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderResp;
import reactor.core.publisher.Mono;

public interface OkxV5TradeRestSpec {

    Mono<AmendOrderResp> amendOrder(AmendOrderReq req);

    Mono<BatchAmendOrderResp> batchAmendOrder(BatchAmendOrderReq req);

    Mono<CancelResp> batchCancelOrder(BatchCancelReq req);

    Mono<BatchOrderResp> batchOrder(BatchOrderReq req);

    Mono<CancelResp> cancelOrder(CancelReq req);

    Mono<ClosePositionResp> closePosition(ClosePositionReq req);

    Mono<FillsResp> fills(FillsReq req);

    Mono<FillsHistoryResp> fillsHistory(FillsHistoryReq req);

    Mono<OrderResp> getOrder(OrderReq req);

    Mono<OrdArchiveResp> orderArchive(OrdArchiveReq req);

    Mono<OrdHisResp> orderHistory(OrdHisReq req);

    Mono<PendingOrdsResp> pendingOrders(PendingOrdsReq req);

    Mono<PlaceOrderResp> placeOrder(PlaceOrderReq req);
}
