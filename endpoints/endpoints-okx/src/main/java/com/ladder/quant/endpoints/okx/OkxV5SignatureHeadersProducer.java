package com.ladder.quant.endpoints.okx;

import java.util.function.Consumer;

import lombok.SneakyThrows;

import org.springframework.http.HttpHeaders;


public class OkxV5SignatureHeadersProducer { // implements HeadersProducer {

    private final String key;
    private final String security;

    public OkxV5SignatureHeadersProducer(String key, String security) {
        this.security = security;
        this.key = key;
    }

//    @Override
//    @SneakyThrows
//    public Consumer<HttpHeaders> produce(Req req) {
//        String signatureContent = req.getSignatureContent();
//        SignatureUtils.SignVo signVo = new SignatureUtils.SignVo();
//        signVo.setRequestParam(signatureContent);
//
//        signVo.setSecretKey(security);
//        signVo.setAccessKey(key);
//        String signature = "";
//        return httpHeaders -> {
//            httpHeaders.add("ApiKey", key);
//            httpHeaders.add("Signature", signature);
//            httpHeaders.add("Request-Time", signVo.getReqTime());
//        };
//    }
}
