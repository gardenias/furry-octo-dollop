package com.p.quant.endpoints.binance.rest.trade.spot;

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
 * 查询目前下单数
 * @author：wanghao
 * @see ""
 * @date  ：Created in 2022/7/7
 */
public class QueryCurrentOrderCountsEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

    public QueryCurrentOrderCountsEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/api/v3/rateLimit/order", HttpMethod.GET);
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
