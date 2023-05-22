package com.g.fod.endpoints.okx.rest;

import lombok.extern.slf4j.Slf4j;

import com.g.common.endpoints.core.rest.HeadersProducer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.g.fod.endpoints.okx.rest.trade.AmendOrderEndpoint;
import com.g.fod.endpoints.okx.rest.trade.BatchAmendOrderEndpoint;
import com.g.fod.endpoints.okx.rest.trade.BatchCancelOrderEndpoint;
import com.g.fod.endpoints.okx.rest.trade.BatchOrderEndpoint;
import com.g.fod.endpoints.okx.rest.trade.CancelOrderEndpoint;
import com.g.fod.endpoints.okx.rest.trade.ClosePositionEndpoint;
import com.g.fod.endpoints.okx.rest.trade.FillsEndpoint;
import com.g.fod.endpoints.okx.rest.trade.FillsHistoryEndpoint;
import com.g.fod.endpoints.okx.rest.trade.GetOrderEndpoint;
import com.g.fod.endpoints.okx.rest.trade.OrderArchiveEndpoint;
import com.g.fod.endpoints.okx.rest.trade.OrderHisotryEndpoint;
import com.g.fod.endpoints.okx.rest.trade.PendingOrdersEndpoint;
import com.g.fod.endpoints.okx.rest.trade.PlaceOrderEndpoint;

@Slf4j
@Component
public class OkxV5TradeRestSpecImpl extends AbstractRestSpec implements OkxV5TradeRestSpec {

  private final AmendOrderEndpoint amendOrder;
  private final BatchAmendOrderEndpoint batchAmendOrder;
  private final BatchCancelOrderEndpoint batchCancelOrder;
  private final BatchOrderEndpoint batchOrder;
  private final CancelOrderEndpoint cancelOrder;
  private final ClosePositionEndpoint closePosition;
  private final FillsEndpoint fills;
  private final FillsHistoryEndpoint fillsHistory;
  private final GetOrderEndpoint getOrder;
  private final OrderArchiveEndpoint orderArchive;
  private final OrderHisotryEndpoint orderHisotry;
  private final PendingOrdersEndpoint pendingOrders;
  private final PlaceOrderEndpoint placeOrder;

  public OkxV5TradeRestSpecImpl(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer);

    this.amendOrder = new AmendOrderEndpoint(webClient, headersProducer);
    this.batchAmendOrder = new BatchAmendOrderEndpoint(webClient, headersProducer);
    this.batchCancelOrder = new BatchCancelOrderEndpoint(webClient, headersProducer);
    this.batchOrder = new BatchOrderEndpoint(webClient, headersProducer);
    this.cancelOrder = new CancelOrderEndpoint(webClient, headersProducer);
    this.closePosition = new ClosePositionEndpoint(webClient, headersProducer);
    this.fills = new FillsEndpoint(webClient, headersProducer);
    this.fillsHistory = new FillsHistoryEndpoint(webClient, headersProducer);
    this.getOrder = new GetOrderEndpoint(webClient, headersProducer);
    this.orderArchive = new OrderArchiveEndpoint(webClient, headersProducer);
    this.orderHisotry = new OrderHisotryEndpoint(webClient, headersProducer);
    this.pendingOrders = new PendingOrdersEndpoint(webClient, headersProducer);
    this.placeOrder = new PlaceOrderEndpoint(webClient, headersProducer);
  }

  @Override
  public Mono<AmendOrderEndpoint.AmendOrderResp> amendOrder(AmendOrderEndpoint.AmendOrderReq req) {
    return amendOrder.exec(req, AmendOrderEndpoint.AmendOrderResp.class);
  }

  @Override
  public Mono<BatchAmendOrderEndpoint.BatchAmendOrderResp> batchAmendOrder(BatchAmendOrderEndpoint.BatchAmendOrderReq req) {
    return batchAmendOrder.exec(req, BatchAmendOrderEndpoint.BatchAmendOrderResp.class);
  }

  @Override
  public Mono<CancelOrderEndpoint.CancelResp> batchCancelOrder(BatchCancelOrderEndpoint.BatchCancelReq req) {
    return batchCancelOrder.exec(req, CancelOrderEndpoint.CancelResp.class);
  }

  @Override
  public Mono<BatchOrderEndpoint.BatchOrderResp> batchOrder(BatchOrderEndpoint.BatchOrderReq req) {
    return batchOrder.exec(req, BatchOrderEndpoint.BatchOrderResp.class);
  }

  @Override
  public Mono<CancelOrderEndpoint.CancelResp> cancelOrder(CancelOrderEndpoint.CancelReq req) {
    return cancelOrder.exec(req, CancelOrderEndpoint.CancelResp.class);
  }

  @Override
  public Mono<ClosePositionEndpoint.ClosePositionResp> closePosition(ClosePositionEndpoint.ClosePositionReq req) {
    return closePosition.exec(req, ClosePositionEndpoint.ClosePositionResp.class);
  }

  @Override
  public Mono<FillsEndpoint.FillsResp> fills(FillsEndpoint.FillsReq req) {
    return fills.exec(req, FillsEndpoint.FillsResp.class);
  }

  @Override
  public Mono<FillsHistoryEndpoint.FillsHistoryResp> fillsHistory(FillsHistoryEndpoint.FillsHistoryReq req) {
    return fillsHistory.exec(req, FillsHistoryEndpoint.FillsHistoryResp.class);
  }

  @Override
  public Mono<GetOrderEndpoint.OrderResp> getOrder(GetOrderEndpoint.OrderReq req) {
    return getOrder.exec(req, GetOrderEndpoint.OrderResp.class);
  }

  @Override
  public Mono<OrderArchiveEndpoint.OrdArchiveResp> orderArchive(OrderArchiveEndpoint.OrdArchiveReq req) {
    return orderArchive.exec(req, OrderArchiveEndpoint.OrdArchiveResp.class);
  }

  @Override
  public Mono<OrderHisotryEndpoint.OrdHisResp> orderHistory(OrderHisotryEndpoint.OrdHisReq req) {
    return orderHisotry.exec(req, OrderHisotryEndpoint.OrdHisResp.class);
  }

  @Override
  public Mono<PendingOrdersEndpoint.PendingOrdsResp> pendingOrders(PendingOrdersEndpoint.PendingOrdsReq req) {
    return pendingOrders.exec(req, PendingOrdersEndpoint.PendingOrdsResp.class);
  }

  @Override
  public Mono<PlaceOrderEndpoint.PlaceOrderResp> placeOrder(PlaceOrderEndpoint.PlaceOrderReq req) {
    return placeOrder.exec(req, PlaceOrderEndpoint.PlaceOrderResp.class);
  }
}
