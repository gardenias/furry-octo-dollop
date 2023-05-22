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
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.g.fod.endpoints.okx.rest.trade.BatchCancelOrderEndpoint.BatchCancelReq;

public class BatchCancelOrderEndpoint extends AbstractRESTEndpoint<BatchCancelReq, CancelOrderEndpoint.CancelResp> {

  public BatchCancelOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/trade/cancel-batch-orders", HttpMethod.POST);
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class BatchCancelReq extends Req implements List<CancelOrderEndpoint.CancelReq> {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    @Delegate
    @JsonIgnore
    private List<CancelOrderEndpoint.CancelReq> data;
  }
}
