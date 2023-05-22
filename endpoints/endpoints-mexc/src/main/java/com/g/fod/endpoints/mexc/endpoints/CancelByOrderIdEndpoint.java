package com.g.fod.endpoints.mexc.endpoints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelReq;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelResp;
import com.p.common.base.domain.OrderId;
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class CancelByOrderIdEndpoint extends
  AbstractRESTEndpoint<CancelReq, CancelResp> {

  public CancelByOrderIdEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v1/private/order/cancel", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true)
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  public static class CancelReq extends Req implements List<OrderId> {

    @Delegate
    @JsonIgnore
    private List<OrderId> orderIds;

    public CancelReq ordId(OrderId... ordIds) {
      if (this.orderIds == null) {this.orderIds = new ArrayList<>(50);}
      for (OrderId ordId : ordIds) {
        if (ordId.getValue() > 0) {this.orderIds.add(ordId);}
      }
      return this;
    }

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
  }

  @Setter
  @Getter
  public static class CancelResp extends Resp {

    private List<CancelResult> data;

    public boolean get(OrderId orderId) {
      if (data.isEmpty()) {
        return false;
      }

      for (CancelResult cancelResult : data) {
        long cancelResultOrdId = cancelResult.getOrderId();
        if (orderId.getValue().compareTo(cancelResultOrdId) == 0) {
          //TODO errCode, errMsg
          return true;
        }
      }
      return false;
    }

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
  }

  @Setter
  @Getter
  public static class CancelResult implements Serializable {

    private long orderId;
    private String errorMsg;
    private String errorCode;

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
  }
}

