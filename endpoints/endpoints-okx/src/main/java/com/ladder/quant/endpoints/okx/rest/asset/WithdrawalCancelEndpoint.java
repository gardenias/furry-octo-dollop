package com.ladder.quant.endpoints.okx.rest.asset;

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
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelReq;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalCancelEndpoint.WithdrawalCancelResp;

public class WithdrawalCancelEndpoint extends AbstractRESTEndpoint<WithdrawalCancelReq, WithdrawalCancelResp> {

  public WithdrawalCancelEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/asset/cancel-withdrawal", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class WithdrawalCancelResp extends Resp {

    private List<Data> data;
  }

  public static class Data implements Serializable {

    // 是	提币申请ID
    private String wdId;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class WithdrawalCancelReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 是	提币申请ID
    private String wdId;
  }
}
