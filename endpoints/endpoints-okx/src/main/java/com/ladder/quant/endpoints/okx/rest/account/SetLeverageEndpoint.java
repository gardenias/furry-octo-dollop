package com.ladder.quant.endpoints.okx.rest.account;

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
import com.ladder.quant.endpoints.okx.rest.account.SetLeverageEndpoint.SetLeverageReq;
import com.ladder.quant.endpoints.okx.rest.account.SetLeverageEndpoint.SetLeverageResp;

public class SetLeverageEndpoint extends AbstractRESTEndpoint<SetLeverageReq, SetLeverageResp> {

  public SetLeverageEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/account/set-leverage", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class SetLeverageResp extends Resp {

    private List<Data> data;
  }

  @Setter
  @Getter
  public static class Data implements Serializable {

    // 	杠杆倍数
    private String lever;
    // 	保证金模式 isolated：逐仓 cross：全仓
    private String mgnMode;
    // 	产品ID
    private String instId;
    // 	持仓方向
    private String posSide;

  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class SetLeverageReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 可选	产品ID：币对、合约 instId和ccy至少要传一个；如果两个都传，默认使用instId
    private String instId;
    // 可选	保证金币种仅适用于跨币种保证金模式的全仓 币币杠杆。设置自动借币的杠杆倍数时必填
    private String ccy;
    // 是	杠杆倍数
    private String lever;
    // 是	保证金模式isolated：逐仓 cross：全仓 如果ccy有效传值，该参数值只能为cross。
    private String mgnMode;
    // 可选	持仓方向long：双向持仓多头 short：双向持仓空头 net：单向持仓 仅适用于逐仓交割/永续 在双向持仓且保证金模式为逐仓条件下必填，且仅可选择 long或short，默认net
    private String posSide;
  }
}
