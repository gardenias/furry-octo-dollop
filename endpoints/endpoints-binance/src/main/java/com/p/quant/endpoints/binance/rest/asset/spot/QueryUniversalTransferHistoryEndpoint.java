package com.p.quant.endpoints.binance.rest.asset.spot;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;

import lombok.Data;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 查询用户万向划转历史
 * @author：wanghao
 * @see ""
 * @date  ：Created in 2022/7/7
 */
public class QueryUniversalTransferHistoryEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

    public QueryUniversalTransferHistoryEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/sapi/v1/asset/transfer", HttpMethod.GET);
    }
    @Data
    public static class AccountInfoReq extends BinanceReq {
        private Long recvWindow;
        private Long timestamp;

    }
    @Data
    public static class AccountInfoResp extends Resp {
      
    }

   
}