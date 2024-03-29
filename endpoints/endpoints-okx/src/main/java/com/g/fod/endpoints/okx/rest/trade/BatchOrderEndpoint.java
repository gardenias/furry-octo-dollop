package com.g.fod.endpoints.okx.rest.trade;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.g.fod.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderReq;
import com.g.fod.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderResp;

public class BatchOrderEndpoint extends AbstractRESTEndpoint<BatchOrderReq, BatchOrderResp> {

  public BatchOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/trade/batch-orders", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class BatchOrderResp extends Resp {

    private List<PlaceOrderEndpoint.PlaceOrderRespData> data;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class BatchOrderReq extends Req implements List<PlaceOrderEndpoint.PlaceOrderReq> {

    @Delegate
    @JsonIgnore
    private List<PlaceOrderEndpoint.PlaceOrderReq> data;

  }

}
