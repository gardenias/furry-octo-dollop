package com.g.fod.endpoints.mexc.endpoints;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Resp;
import com.g.common.endpoints.core.rest.SymbolReq;
import com.g.fod.endpoints.mexc.endpoints.CancelAllEndpoint.CancelAllReq;
import com.g.fod.endpoints.mexc.endpoints.CancelAllEndpoint.CancelAllResp;
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class CancelAllEndpoint extends
  AbstractRESTEndpoint<CancelAllReq, CancelAllResp> {

  public CancelAllEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v1/private/order/cancel_all", HttpMethod.POST);
  }

  public static class CancelAllResp extends Resp {

  }

  @Getter
  @Setter
  @Accessors(chain = true, fluent = true)
  public static class CancelAllReq extends SymbolReq {

    public CancelAllReq(String symbol) {
      super(symbol);
    }

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }
  }
}
