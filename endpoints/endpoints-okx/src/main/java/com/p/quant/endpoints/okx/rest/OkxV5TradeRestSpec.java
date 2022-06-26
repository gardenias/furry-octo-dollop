package com.p.quant.endpoints.okx.rest;

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
