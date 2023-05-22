package com.g.fod.endpoints.okx.rest.trade;

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

import com.g.fod.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelReq;
import com.g.fod.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelResp;

public class CancelOrderEndpoint extends AbstractRESTEndpoint<CancelReq, CancelResp> {

  public CancelOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/trade/cancel-order", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class CancelResp extends Resp {

    private List<CancelRespData> data;
  }

  @Setter
  @Getter
  public static class CancelRespData implements Serializable {

    // 	订单ID
    private String ordId;
    // 	客户自定义订单ID
    private String clOrdId;
    // 	事件执行结果的code，0代表成功
    private String sCode;
    // 事件执行失败时的msg
    private String sMsg;

  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class CancelReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 		是	产品ID，如 BTC-USD-190927
    private String instId;
    // 		可选	订单ID， ordId和clOrdId必须传一个，若传两个，以ordId为主
    private String ordId;
    // 		可选	用户自定义ID
    private String clOrdId;

  }

}
