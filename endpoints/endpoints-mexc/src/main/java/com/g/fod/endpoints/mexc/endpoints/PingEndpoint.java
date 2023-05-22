package com.g.fod.endpoints.mexc.endpoints;

import lombok.Getter;
import lombok.Setter;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.EmptyReq;
import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.mexc.endpoints.PingEndpoint.PingResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class PingEndpoint extends AbstractRESTEndpoint<EmptyReq, PingResp> {

  public PingEndpoint(WebClient webClient) {
    super(webClient, "/api/v1/contract/ping", HttpMethod.GET);
  }

  @Getter
  @Setter
  public static class PingResp extends Resp {

    private long data;
  }
}
