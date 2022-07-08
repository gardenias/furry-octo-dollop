package com.p.quant.endpoints.binance.rest1.asset.margin;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest1.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.p.quant.endpoints.binance.rest1.account.spot.AccountInfoEndpoint.AccountInfoResp;

import lombok.Data;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 杠杆账户借贷
 * @author：wanghao
 * @see ""
 * @date  ：Created in 2022/7/7
 */
public class MarginAccountBorrowEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

    public MarginAccountBorrowEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/sapi/v1/margin/loan", HttpMethod.GET);
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
