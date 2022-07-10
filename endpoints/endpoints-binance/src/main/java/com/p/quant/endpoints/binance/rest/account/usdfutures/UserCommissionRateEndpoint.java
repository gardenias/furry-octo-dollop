package com.p.quant.endpoints.binance.rest.account.usdfutures;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.account.usdfutures.UserCommissionRateEndpoint.UserCommissionRateReq;
import com.p.quant.endpoints.binance.rest.account.usdfutures.UserCommissionRateEndpoint.UserCommissionRateResp;

import lombok.Data;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * 用户手续费率 (USER_DATA)
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#user_data-12"
 * @date  ：Created in 2022/7/7
 */
public class UserCommissionRateEndpoint extends AbstractBinanceRESTEndpoint<UserCommissionRateReq, UserCommissionRateResp> {

    public UserCommissionRateEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v1/commissionRate", HttpMethod.POST);
    }
    @Data
    public static class UserCommissionRateReq extends BinanceReq {
        private Long recvWindow;
        private Long timestamp;
        private String symbol;

    }
    @Data
    public static class UserCommissionRateResp extends Resp {
        private String  symbol;
        private String  makerCommissionRate;
        private String  takerCommissionRate;
    }

   
}
