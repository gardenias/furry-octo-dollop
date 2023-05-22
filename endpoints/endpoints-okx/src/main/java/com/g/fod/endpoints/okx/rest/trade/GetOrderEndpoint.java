package com.g.fod.endpoints.okx.rest.trade;

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

import com.g.fod.endpoints.okx.domain.Order;
import com.g.fod.endpoints.okx.rest.trade.GetOrderEndpoint.OrderReq;
import com.g.fod.endpoints.okx.rest.trade.GetOrderEndpoint.OrderResp;

public class GetOrderEndpoint extends AbstractRESTEndpoint<OrderReq, OrderResp> {

  public GetOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/trade/order", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class OrderResp extends Resp {

    private List<Order> data;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class OrderReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    //	是	产品ID ，如BTC-USD-190927
    private String instId;
    //	可选	订单ID ， ordId和clOrdId必须传一个，若传两个，以ordId为主
    private String ordId;
    //	可选	用户自定义ID
    private String clOrdId;

  }

}
