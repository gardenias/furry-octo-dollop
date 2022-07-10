package com.p.quant.endpoints.binance.rest.market.usdfutures;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;

import lombok.Data;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * 查询历史成交
 * @author：wanghao
 * @date  ：Created in 2022/7/7
 */
public class RecentTradesListEndpoint extends AbstractBinanceRESTEndpoint<RecentTradesListReq, RecentTradesListResp> {

    public RecentTradesListEndpoint(WebClient webClient,  SignatureProducer signatureProducer) {
        super(webClient, "/fapi/v1/trades", HttpMethod.GET);
    }

    @Data
    public static class RecentTradesListResp extends Resp {
        private Integer id;
        private String price;
        private String qty;
        private Long time;
        private boolean isBuyerMaker;
        private boolean isBestMatch;
    }
    
    @Data
    public static class RecentTradesListReq extends BinanceReq {
        private String symbol;// 必填
        private Integer limit;//
     
    }
}
