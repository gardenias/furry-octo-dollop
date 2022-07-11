package com.p.quant.endpoints.binance.rest.market.spot;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.enums.RequestType;
import com.p.quant.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookReq;

import com.p.quant.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookResp;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * 深度信息
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/spot/cn/#38a975b802"
 * @date  ：Created in 2022/7/7
 */
public class OrderBookEndpoint extends AbstractBinanceRESTEndpoint<OrderBookReq, OrderBookResp> {

    public OrderBookEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/api/v3/depth", HttpMethod.GET);
    }
    @Data
    public static class OrderBookReq extends BinanceReq {
        private Integer limit;
        private String symbol;

    }
    @Data
    public static class OrderBookResp extends Resp {
        private long lastUpdateId;
        private List<List<String>> bids;
        private List<List<String>> asks;
    }

   
}
