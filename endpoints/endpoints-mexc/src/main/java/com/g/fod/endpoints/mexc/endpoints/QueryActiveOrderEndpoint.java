package com.g.fod.endpoints.mexc.endpoints;

import java.util.List;

import com.g.fod.endpoints.mexc.domain.Order;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.mexc.endpoints.QueryActiveOrderEndpoint.QueryReq;
import com.g.fod.endpoints.mexc.endpoints.QueryActiveOrderEndpoint.QueryResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class QueryActiveOrderEndpoint extends AbstractRESTEndpoint<QueryReq, QueryResp> {

  public QueryActiveOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v1/private/order/list/open_orders/{symbol}", HttpMethod.GET);
  }

  @Setter
  @Getter
  public static class QueryReq extends Req {

    private String symbol;

    public QueryReq(String symbol) {
      this.symbol = symbol;
    }

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
  }

  @Setter
  @Getter
  public static class QueryResp extends Resp {

    private List<Order> data;

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
  }
}
