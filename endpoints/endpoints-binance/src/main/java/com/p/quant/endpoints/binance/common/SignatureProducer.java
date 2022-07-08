package com.p.quant.endpoints.binance.common;

import com.p.quant.endpoints.binance.base.BinanceReq;

import com.p.quant.endpoints.binance.enums.RequestType;

import lombok.SneakyThrows;

import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

public interface SignatureProducer {
    SignatureProducer NON_SIGNATURE_PRODUCER = new NonSignatureProducer();

    String generateSignature(String queryString);

    @SneakyThrows
    Consumer<HttpHeaders> produceHeaders(BinanceReq req, RequestType requestType);
}
