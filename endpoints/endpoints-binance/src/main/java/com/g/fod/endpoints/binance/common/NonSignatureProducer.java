package com.g.fod.endpoints.binance.common;

import java.util.function.Consumer;

import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.enums.RequestType;
import org.springframework.http.HttpHeaders;

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
