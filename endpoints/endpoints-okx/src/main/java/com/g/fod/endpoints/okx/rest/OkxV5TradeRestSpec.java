package com.g.fod.endpoints.okx.rest;

import com.g.fod.endpoints.okx.rest.trade.*;
import reactor.core.publisher.Mono;

import com.g.fod.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderReq;
import com.g.fod.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderResp;
import com.g.fod.endpoints.okx.rest.trade.FillsEndpoint.FillsReq;
import com.g.fod.endpoints.okx.rest.trade.FillsEndpoint.FillsResp;
import com.g.fod.endpoints.okx.rest.trade.OrderArchiveEndpoint.OrdArchiveReq;
import com.g.fod.endpoints.okx.rest.trade.OrderArchiveEndpoint.OrdArchiveResp;
import com.g.fod.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsReq;
import com.g.fod.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsResp;

public interface OkxV5TradeRestSpec {

  Mono<AmendOrderResp> amendOrder(AmendOrderReq req);

  Mono<BatchAmendOrderEndpoint.BatchAmendOrderResp> batchAmendOrder(BatchAmendOrderEndpoint.BatchAmendOrderReq req);

  Mono<CancelOrderEndpoint.CancelResp> batchCancelOrder(BatchCancelOrderEndpoint.BatchCancelReq req);

  Mono<BatchOrderEndpoint.BatchOrderResp> batchOrder(BatchOrderEndpoint.BatchOrderReq req);

  Mono<CancelOrderEndpoint.CancelResp> cancelOrder(CancelOrderEndpoint.CancelReq req);

  Mono<ClosePositionEndpoint.ClosePositionResp> closePosition(ClosePositionEndpoint.ClosePositionReq req);

  Mono<FillsResp> fills(FillsReq req);

  Mono<FillsHistoryEndpoint.FillsHistoryResp> fillsHistory(FillsHistoryEndpoint.FillsHistoryReq req);

  Mono<GetOrderEndpoint.OrderResp> getOrder(GetOrderEndpoint.OrderReq req);

  Mono<OrdArchiveResp> orderArchive(OrdArchiveReq req);

  Mono<OrderHisotryEndpoint.OrdHisResp> orderHistory(OrderHisotryEndpoint.OrdHisReq req);

  Mono<PendingOrdsResp> pendingOrders(PendingOrdsReq req);

  Mono<PlaceOrderEndpoint.PlaceOrderResp> placeOrder(PlaceOrderEndpoint.PlaceOrderReq req);
}
