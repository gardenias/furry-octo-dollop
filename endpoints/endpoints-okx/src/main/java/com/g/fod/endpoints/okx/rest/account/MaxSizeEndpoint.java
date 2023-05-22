package com.g.fod.endpoints.okx.rest.account;

import java.io.Serializable;
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

import com.g.fod.endpoints.okx.rest.account.MaxSizeEndpoint.MaxSizeReq;
import com.g.fod.endpoints.okx.rest.account.MaxSizeEndpoint.MaxSizeResp;

public class MaxSizeEndpoint extends AbstractRESTEndpoint<MaxSizeReq, MaxSizeResp> {

  public MaxSizeEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/account/max-size", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class MaxSizeResp extends Resp {

    private List<Data> data;
  }

  @Setter
  @Getter
  public static class Data implements Serializable {

    // 	产品ID
    private String instId;
    // 	保证金币种
    private String ccy;
    // 	币币/币币杠杆：最大可买的交易币数量 单币种保证金模式下的全仓杠杆订单，为交易币数量 交割/永续/期权：最大可开多的合约张数
    private String maxBuy;
    // 	币币/币币杠杆：最大可卖的计价币数量 单币种保证金模式下的全仓杠杆订单，为交易币数量 交割/永续/期权：最大可开空的合约张数
    private String maxSell;

  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class MaxSizeReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    //  	是	产品ID，如 BTC-USDT 支持多产品ID查询（不超过5个），半角逗号分隔
    private String instId;
    //  	是	交易模式cross：全仓 isolated：逐仓 cash：非保证金
    private String tdMode;
    //  	可选	保证金币种，仅适用于单币种保证金模式下的全仓杠杆订单
    private String ccy;
    //  	否	委托价格当不填委托价时会按当前最新成交价计算当指定多个产品ID查询时，忽略该参数，按当前最新成交价计算
    private String px;
    //  	否	开仓杠杆倍数默认为当前杠杆倍数仅适用于币币杠杆/交割/永续
    private String leverage;
  }
}
