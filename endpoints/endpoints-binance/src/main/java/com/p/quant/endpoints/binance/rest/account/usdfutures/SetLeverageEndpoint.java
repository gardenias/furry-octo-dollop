package com.p.quant.endpoints.binance.rest.account.usdfutures;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.account.usdfutures.SetLeverageEndpoint.SetLeverageReq;
import com.p.quant.endpoints.binance.rest.account.usdfutures.SetLeverageEndpoint.SetLeverageResp;

import lombok.Data;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * 调整开仓杠杆 (TRADE)
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#trade-10"
 * @date  ：Created in 2022/7/7
 */
public class SetLeverageEndpoint extends AbstractBinanceRESTEndpoint<SetLeverageReq, SetLeverageResp> {

    public SetLeverageEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v1/leverage", HttpMethod.POST);
    }
    @Data
    public static class SetLeverageReq extends BinanceReq {
        private Long recvWindow;
        private Long timestamp;
        private String symbol;//交易对
        private Integer leverage;//目标杠杆倍数：1 到 125 整数

    }
    @Data
    public static class SetLeverageResp extends Resp {
        private Integer  leverage;
        private String  maxNotionalValue; // 当前杠杆倍数下允许的最大名义价值
        private String  symbol;// 交易对
    }

   
}
