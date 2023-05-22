package com.g.fod.endpoints.mexc;

import java.util.function.Consumer;

import lombok.SneakyThrows;

import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.p.common.base.SignatureUtils;
import org.springframework.http.HttpHeaders;

public class MexcSignatureHeadersProducer implements HeadersProducer {

  private final String key;
  private final String security;

  public MexcSignatureHeadersProducer(String key, String security) {
    this.security = security;
    this.key = key;
  }

  @Override
  @SneakyThrows
  public Consumer<HttpHeaders> produce(Req req) {
    String signatureContent = req.getSignatureContent();
    SignatureUtils.SignVo signVo = new SignatureUtils.SignVo();
    signVo.setRequestParam(signatureContent);

    signVo.setSecretKey(security);
    signVo.setAccessKey(key);
    String signature = SignatureUtils.signature(signVo);
    return httpHeaders -> {
      httpHeaders.add("ApiKey", key);
      httpHeaders.add("Signature", signature);
      httpHeaders.add("Request-Time", signVo.getReqTime());
    };
  }
}
