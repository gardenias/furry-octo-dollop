package com.ladder.quant.endpoints.okx.rest;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import com.ladder.quant.endpoints.okx.rest.GetTemplateEndpoint.GetTemplateReq;
import com.ladder.quant.endpoints.okx.rest.GetTemplateEndpoint.GetTemplateResp;

class GetTemplateEndpoint extends AbstractRESTEndpoint<GetTemplateReq, GetTemplateResp> {

  public GetTemplateEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "TODO", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class GetTemplateResp extends Resp {

    private List<Data> data;
  }

  @Setter
  @Getter
  public static class Data implements Serializable {

  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class GetTemplateReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

  }
}
