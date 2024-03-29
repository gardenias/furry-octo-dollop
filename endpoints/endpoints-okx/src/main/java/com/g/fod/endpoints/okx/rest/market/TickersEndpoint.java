package com.g.fod.endpoints.okx.rest.market;

import java.util.List;

import com.g.fod.endpoints.okx.domain.Ticker;
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
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.g.fod.endpoints.okx.rest.market.TickersEndpoint.TickersReq;
import com.g.fod.endpoints.okx.rest.market.TickersEndpoint.TickersResp;

public class TickersEndpoint extends AbstractRESTEndpoint<TickersReq, TickersResp> {

  public TickersEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/market/tickers", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class TickersResp extends Resp {

    @Delegate
    @JsonIgnore
    private List<Ticker> data;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class TickersReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 	是	产品类型SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
    private String instType;
    // 	否	标的指数，仅适用于交割/永续/期权 ，如 BTC-USD
    private String uly;

  }
}
