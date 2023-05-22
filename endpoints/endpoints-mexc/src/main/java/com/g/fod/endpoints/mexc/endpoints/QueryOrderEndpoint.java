package com.g.fod.endpoints.mexc.endpoints;

import com.g.fod.endpoints.mexc.domain.Order;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.mexc.endpoints.QueryOrderEndpoint.QueryReq;
import com.g.fod.endpoints.mexc.endpoints.QueryOrderEndpoint.QueryResp;
import com.p.common.base.domain.OrderId;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class QueryOrderEndpoint extends AbstractRESTEndpoint<QueryReq, QueryResp> {

  public QueryOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "api/v1/private/order/get/{order_id}", HttpMethod.GET);
  }

  @Setter
  @Getter
  public static class QueryReq extends Req {

    private OrderId orderId;

    public QueryReq(OrderId orderId) {
      this.orderId = orderId;
    }

    public QueryReq(Long orderId) {
      this.orderId = new OrderId(orderId);
    }

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
  }

  @Setter
  @Getter
  public static class QueryResp extends Resp {

    private Order data;

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
  }

}
