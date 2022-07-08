package com.p.quant.endpoints.binance.common;

import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.enums.RequestType;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

/**
 * @author ：wanghao
 * @date ：Created in 2022/7/8 17:35
 * @description：
 * @modified By：
 * @version: $
 */
public class NonSignatureProducer implements SignatureProducer {
 
    @Override
    public String generateSignature(String queryString) {
        return "";
    }

  
    @Override
    public Consumer<HttpHeaders> produceHeaders(BinanceReq req, RequestType requestType) {
        return (httpHeaders) -> {
        };
    }
}
