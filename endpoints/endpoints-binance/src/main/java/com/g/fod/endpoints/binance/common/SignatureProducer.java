package com.g.fod.endpoints.binance.common;

import java.util.function.Consumer;

import lombok.SneakyThrows;

import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.enums.RequestType;
import org.springframework.http.HttpHeaders;

public interface SignatureProducer {

  SignatureProducer NON_SIGNATURE_PRODUCER = new NonSignatureProducer();

  String generateSignature(String queryString);

  @SneakyThrows
  Consumer<HttpHeaders> produceHeaders(BinanceReq req, RequestType requestType);
}
