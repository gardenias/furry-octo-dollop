//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.g.fod.endpoints.binance.common;

import java.util.function.Consumer;

import lombok.SneakyThrows;

import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.enums.RequestType;
import com.g.fod.endpoints.binance.rest.utils.SignatureGenerator;
import org.springframework.http.HttpHeaders;

public class BinanceSignatureProducer implements SignatureProducer {

  private final String apiKey;
  private final String secretKey;

  public BinanceSignatureProducer(String apiKey, String secretKey) {
    this.apiKey = apiKey;
    this.secretKey = secretKey;

  }

  @Override
  public String generateSignature(String queryString) {
    return SignatureGenerator.getSignature(queryString, secretKey);
  }

  @SneakyThrows
  @Override
  public Consumer<HttpHeaders> produceHeaders(BinanceReq req, RequestType requestType) {
    if (RequestType.PUBLIC.equals(requestType)) {
      return (httpHeaders) -> {};
    }
    return httpHeaders -> {
      httpHeaders.add("X-MBX-APIKEY", apiKey);
    };
  }
}
