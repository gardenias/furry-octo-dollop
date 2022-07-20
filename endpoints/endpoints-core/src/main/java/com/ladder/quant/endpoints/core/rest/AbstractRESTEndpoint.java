package com.ladder.quant.endpoints.core.rest;

import lombok.val;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Mono;

public class AbstractRESTEndpoint<T extends Req, K extends Resp> {

  private final WebClient webClient;
  private final String path;
  private final HttpMethod method;
  private final HeadersProducer headersProducer;

  public AbstractRESTEndpoint(WebClient webClient, String path, HttpMethod method) {
    this(webClient, HeadersProducer.NON_OP_HEADERS_PRODUCER, path, method);
  }

  public AbstractRESTEndpoint(WebClient webClient, HeadersProducer headersProducer,
    String path, HttpMethod method) {
    this.webClient = webClient;
    this.headersProducer = headersProducer;
    this.path = path;
    this.method = method;
  }

  public Mono<K> exec(T req, Class<K> clazz, Object... uriVariables) {
    return body(method, req, uriVariables)
      .headers(headersProducer.produce(req))
      .retrieve()
      .bodyToMono(clazz)
      .retry(3);
  }

  private RequestHeadersSpec<?> body(HttpMethod method, T req, Object... uriVariables) {
    val spec = webClient.method(method).uri(path, uriVariables);

    if (method == HttpMethod.GET || method == HttpMethod.DELETE
      || method == HttpMethod.HEAD || method == HttpMethod.OPTIONS) {
      return spec;
    }

    return spec.bodyValue(req);
  }

}
