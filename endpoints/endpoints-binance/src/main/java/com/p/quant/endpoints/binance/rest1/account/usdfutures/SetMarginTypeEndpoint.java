package com.p.quant.endpoints.binance.rest1.account.usdfutures;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest1.account.usdfutures.SetMarginTypeEndpoint.SetMarginTypeReq;
import com.p.quant.endpoints.binance.rest1.account.usdfutures.SetMarginTypeEndpoint.SetMarginTypeResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 变换逐全仓模式 (TRADE)
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#trade-10"
 * @date  ：Created in 2022/7/7
 */
public class SetMarginTypeEndpoint extends AbstractBinanceRESTEndpoint<SetMarginTypeReq, SetMarginTypeResp> {

    public SetMarginTypeEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v1/marginType", HttpMethod.POST);
    }
    @Data
    public static class SetMarginTypeReq extends BinanceReq {
        private Long recvWindow;
        private Long timestamp;
        private String marginType;//保证金模式 ISOLATED(逐仓), CROSSED(全仓)
        private String symbol;

    }
    @Data
    public static class SetMarginTypeResp extends Resp {
      
    }

   
}
