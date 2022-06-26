package com.p.quant.endpoints.okx.rest;

import com.p.quant.endpoints.okx.rest.trade.AmendOrderEndpoint;
import com.p.quant.endpoints.okx.rest.trade.BatchAmendOrderEndpoint;
import com.p.quant.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderReq;
import com.p.quant.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderResp;
import com.p.quant.endpoints.okx.rest.trade.BatchCancelOrderEndpoint;
import com.p.quant.endpoints.okx.rest.trade.BatchOrderEndpoint;
import com.p.quant.endpoints.okx.rest.trade.CancelOrderEndpoint;
import com.p.quant.endpoints.okx.rest.trade.ClosePositionEndpoint;
import com.p.quant.endpoints.okx.rest.trade.FillsEndpoint;
import com.p.quant.endpoints.okx.rest.trade.FillsHistoryEndpoint;
import com.p.quant.endpoints.okx.rest.trade.FillsHistoryEndpoint.FillsHistoryReq;
import com.p.quant.endpoints.okx.rest.trade.FillsHistoryEndpoint.FillsHistoryResp;
import com.p.quant.endpoints.okx.rest.trade.GetOrderEndpoint;
import com.p.quant.endpoints.okx.rest.trade.OrderArchiveEndpoint;
import com.p.quant.endpoints.okx.rest.trade.OrderHisotryEndpoint;
import com.p.quant.endpoints.okx.rest.trade.OrderHisotryEndpoint.OrdHisReq;
import com.p.quant.endpoints.okx.rest.trade.OrderHisotryEndpoint.OrdHisResp;
import com.p.quant.endpoints.okx.rest.trade.PendingOrdersEndpoint;
import com.p.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint;
import com.p.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderReq;
import com.p.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderResp;
import com.p.quant.endpoints.rest.HeadersProducer;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
    public Mono<BatchAmendOrderResp> batchAmendOrder(BatchAmendOrderReq req) {
        return batchAmendOrder.exec(req, BatchAmendOrderResp.class);
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
    public Mono<FillsHistoryResp> fillsHistory(FillsHistoryReq req) {
        return fillsHistory.exec(req, FillsHistoryResp.class);
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
    public Mono<OrdHisResp> orderHistory(OrdHisReq req) {
        return orderHisotry.exec(req, OrdHisResp.class);
    }

    @Override
    public Mono<PendingOrdersEndpoint.PendingOrdsResp> pendingOrders(PendingOrdersEndpoint.PendingOrdsReq req) {
        return pendingOrders.exec(req, PendingOrdersEndpoint.PendingOrdsResp.class);
    }

    @Override
    public Mono<PlaceOrderResp> placeOrder(PlaceOrderReq req) {
        return placeOrder.exec(req, PlaceOrderResp.class);
    }
}
