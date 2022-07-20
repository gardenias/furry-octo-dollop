package com.ladder.quant.endpoints.okx.rest.market;

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
import com.ladder.quant.endpoints.okx.rest.market.CandlesEndpoint.CandlesReq;
import com.ladder.quant.endpoints.okx.rest.market.CandlesEndpoint.CandlesResp;

public class CandlesEndpoint extends AbstractRESTEndpoint<CandlesReq, CandlesResp> {

  public CandlesEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/market/candles", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class CandlesResp extends Resp {

    private List<Data> data;
  }

  @Setter
  @Getter
  public static class Data implements Serializable {

    // 	数据生成的时间，Unix时间戳的毫秒数格式，如 1597026383085
    private String ts;
    // 	开盘价格
    private String o;
    // 	最高价格
    private String h;
    // 	最低价格
    private String l;
    // 	收盘价格
    private String c;
    // 	交易量，以张为单位 如果是衍生品合约，数值为合约的张数。如果是币币/币币杠杆，数值为交易货币的数量。
    private String vol;
    // 	交易量，以币为单位 如果是衍生品合约，数值为交易货币的数量。如果是币币/币币杠杆，数值为计价货币的数量。
    private String volCcy;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class CandlesReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    //是	产品ID，如BTC-USD-190927-5000-C
    private String instId;
    /**
     * <pre>
     * 	时间粒度，默认值1m 如 [1m/3m/5m/15m/30m/1H/2H/4H]
     *  香港时间开盘价k线：[6H/12H/1D/2D/3D/1W/1M/3M/6M/1Y]
     *  UTC时间开盘价k线：[/6Hutc/12Hutc/1Dutc/2Dutc/3Dutc/1Wutc/1Mutc/3Mutc/6Mutc/1Yutc]
     *  </pre>
     */
    private String bar;
    //否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts
    private String after;
    //否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts
    private String before;
    //否	分页返回的结果集数量，最大为300，不填默认返回100条
    private String limit;

  }
}
