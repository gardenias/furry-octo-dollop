package com.g.fod.endpoints.okx.rest.asset;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.g.fod.endpoints.okx.domain.DepositAdd;
import com.g.fod.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddReq;
import com.g.fod.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddResp;

public class DepositAddEndpoint extends AbstractRESTEndpoint<DepositAddReq, DepositAddResp> {

  public DepositAddEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/asset/deposit-address", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class DepositAddResp extends Resp {

    private List<DepositAdd> data;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class DepositAddReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 是	币种，如BTC
    private String ccy;
  }
}
