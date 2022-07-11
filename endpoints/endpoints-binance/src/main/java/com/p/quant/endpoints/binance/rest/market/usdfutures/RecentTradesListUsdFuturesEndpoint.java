package com.p.quant.endpoints.binance.rest.market.usdfutures;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesReq;
import com.p.quant.endpoints.binance.rest.market.usdfutures.RecentTradesListUsdFuturesEndpoint.RecentTradesListUsdFuturesResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * 查询历史成交
 * @author：wanghao
 * @date  ：Created in 2022/7/7
 */
public class RecentTradesListUsdFuturesEndpoint extends AbstractBinanceRESTEndpoint<RecentTradesListUsdFuturesReq, RecentTradesListUsdFuturesResp> {

    public RecentTradesListUsdFuturesEndpoint(WebClient webClient,  SignatureProducer signatureProducer) {
        super(webClient, "/fapi/v1/trades", HttpMethod.GET);
    }

    @Data
    public static class RecentTradesListUsdFuturesResp extends Resp {
        private Integer id;
        private String price;
        private String qty;
        private Long time;
        private boolean isBuyerMaker;
        private boolean isBestMatch;
    }
    
    @Data
    public static class RecentTradesListUsdFuturesReq extends BinanceReq {
        private String symbol;// 必填
        private Integer limit;//
     
    }
}
