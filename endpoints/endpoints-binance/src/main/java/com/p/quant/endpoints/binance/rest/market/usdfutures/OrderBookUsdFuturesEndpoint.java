package com.p.quant.endpoints.binance.rest.market.usdfutures;

import java.util.List;

import com.p.quant.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesReq;

import com.p.quant.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesResp;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 深度信息
 * @author：wanghao
 * @see ""
 * @date  ：Created in 2022/7/7
 */
public class OrderBookUsdFuturesEndpoint extends AbstractBinanceRESTEndpoint<OrderBookUsdFuturesReq, OrderBookUsdFuturesResp> {

    public OrderBookUsdFuturesEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v1/depth", HttpMethod.GET);
    }
    @Data
    public static class OrderBookUsdFuturesReq extends BinanceReq {
        private String symbol;
        private Integer limit;

    }
    @Data
    public static class OrderBookUsdFuturesResp extends Resp {
        private long lastUpdateId;
        private long E;
        private long T;
        private List<List<String>> bids;
        private List<List<String>> asks;
    }

   
}
