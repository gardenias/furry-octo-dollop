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

import com.g.fod.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderReq;
import com.g.fod.endpoints.okx.rest.trade.BatchAmendOrderEndpoint.BatchAmendOrderResp;

public class BatchAmendOrderEndpoint extends AbstractRESTEndpoint<BatchAmendOrderReq, BatchAmendOrderResp> {

  public BatchAmendOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/trade/amend-batch-orders", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class BatchAmendOrderResp extends Resp {

    private List<AmendOrderEndpoint.Data> data;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class BatchAmendOrderReq extends Req implements List<AmendOrderEndpoint.AmendOrderReq> {

    @Delegate
    @JsonIgnore
    private List<AmendOrderEndpoint.AmendOrderReq> data;
  }

}
