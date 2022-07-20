package com.ladder.quant.endpoints.okx.rest.pub;

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

public class FundingRateEndpoint extends
  AbstractRESTEndpoint<FundingRateEndpoint.FundingRateReq, FundingRateEndpoint.FundingRateResp> {

  public FundingRateEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/public/funding-rate", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class FundingRateResp extends Resp {

    private List<Data> data;
  }

  @Setter
  @Getter
  public static class Data implements Serializable {

    // 	产品类型 SWAP：永续合约
    private String instType;
    // 	产品ID，如BTC-USD-SWAP
    private String instId;
    // 	资金费率
    private String fundingRate;
    // 	下一期预测资金费率
    private String nextFundingRate;
    // 	资金费时间 ，Unix时间戳的毫秒数格式，如 1597026383085
    private String fundingTime;
    // 	下一期资金费时间 ，Unix时间戳的毫秒数格式，如 1622851200000
    private String nextFundingTime;

  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class FundingRateReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    //	是	产品ID ，如 BTC-USD-SWAP 仅适用于永续
    private String instId;
  }
}
