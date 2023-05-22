//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.g.fod.endpoints.binance.base;

import java.util.Map;
import java.util.Objects;

import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.enums.RequestType;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.utils.UrlBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class AbstractBinanceRESTEndpoint<T extends BinanceReq, K extends Resp> {

  private final WebClient webClient;
  private final String path;
  private final HttpMethod method;
  private final SignatureProducer signatureProducer;
  private final RequestType requestType;

  public AbstractBinanceRESTEndpoint(WebClient webClient, String path, HttpMethod method) {
    this(webClient, SignatureProducer.NON_SIGNATURE_PRODUCER, path, method, RequestType.PUBLIC);
  }

  public AbstractBinanceRESTEndpoint(WebClient webClient, SignatureProducer signatureProducer, String path,
    HttpMethod method) {
    this(webClient, signatureProducer, path, method, RequestType.PUBLIC);
  }

  public AbstractBinanceRESTEndpoint(WebClient webClient,
    SignatureProducer signatureProducer, String path, HttpMethod method, RequestType requestType) {
    this.webClient = webClient;
    this.path = path;
    this.method = method;
    this.signatureProducer = signatureProducer;
    this.requestType = requestType;
  }

  public Mono<K> exec(T req, Class<K> clazz, Object... uriVariables) {
    return this.webClient.method(method).uri(buildUri(path, req), uriVariables)
      .headers(this.signatureProducer.produceHeaders(req,
        requestType)).retrieve().bodyToMono(clazz).doOnError(WebClientResponseException.class, err -> {
        log.error("exec fail,response body：{}", err.getResponseBodyAsString());
      }).retry(3L);
  }

  private WebClient.RequestHeadersSpec<?> body(HttpMethod method, T req, Object... uriVariables) {
    WebClient.RequestBodySpec spec = this.webClient.method(method).uri(buildUri(path, req), uriVariables);
    return method != HttpMethod.GET && method != HttpMethod.DELETE && method != HttpMethod.HEAD
      && method != HttpMethod.OPTIONS ? spec.bodyValue(req) : spec;
  }

  public Flux<K> execFlux(T req, Class<K> clazz, Object... uriVariables) {
    return this.webClient.method(method).uri(buildUri(path, req), uriVariables)
      .headers(this.signatureProducer.produceHeaders(req, requestType)).retrieve().bodyToFlux(clazz)
      .doOnError(WebClientResponseException.class, err -> {
        log.error("execGetFlux fail,response body：{}", err.getResponseBodyAsString());
      }).retry(3L);
  }

  public Mono<String> execFluxString(T req, Object... uriVariables) {
    return this.webClient.method(method).uri(buildUri(path, req), uriVariables)
      .headers(this.signatureProducer.produceHeaders(req, requestType)).retrieve().bodyToMono(String.class)
      .doOnError(WebClientResponseException.class, err -> {
        log.error("execFluxString fail,response body：{}", err.getResponseBodyAsString());
      }).retry(3L);
  }

  private String buildUri(String path, T req) {
    if (Objects.nonNull(req)) {
      Map<String, Object> parameters = JSONObject.parseObject(JSONObject.toJSONString(req),
        new TypeReference<Map<String, Object>>() {
        });
      String queryString = UrlBuilder.joinQueryParameters(parameters);
      StringBuilder sb = new StringBuilder(path);
      sb.append('?').append(queryString);
      if (RequestType.SIGNED.equals(requestType)) {
        String signature = signatureProducer.generateSignature(queryString);
        if (StringUtils.isNotBlank(signature)) {
          sb.append("&signature=").append(signature);
        }
      }
      return sb.toString();
    }
    return path;
  }
}
