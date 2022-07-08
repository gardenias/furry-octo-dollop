package com.p.quant.endpoints.binance.rest1.account.usdfutures;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest1.account.usdfutures.GetPositionMarginChangeHistoryEndpoint.GetPositionMarginChangeHistoryReq;
import com.p.quant.endpoints.binance.rest1.account.usdfutures.GetPositionMarginChangeHistoryEndpoint.GetPositionMarginChangeHistoryResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 逐仓保证金变动历史
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#trade-13"
 * @date  ：Created in 2022/7/7
 */
public class GetPositionMarginChangeHistoryEndpoint extends AbstractBinanceRESTEndpoint<GetPositionMarginChangeHistoryReq, GetPositionMarginChangeHistoryResp> {

    public GetPositionMarginChangeHistoryEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v1/positionMargin/history", HttpMethod.GET);
    }
    @Data
    public static class GetPositionMarginChangeHistoryReq extends BinanceReq {
        private Long recvWindow;
        private Long timestamp;
        private Long startTime;
        private Long endTime;
        private Integer limit;
        private String symbol;//交易对
        private Integer type;//调整方向 1: 增加逐仓保证金，2: 减少逐仓保证金

    }
    @Data
    public static class GetPositionMarginChangeHistoryResp extends Resp {
        private String amount;
        private String asset;
        private String symbol;
        private long time;
        private int type;
        private String positionSide;
    }

   
}
