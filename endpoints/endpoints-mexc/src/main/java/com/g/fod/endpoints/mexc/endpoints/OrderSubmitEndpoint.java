package com.g.fod.endpoints.mexc.endpoints;

import java.math.BigDecimal;

import com.g.fod.endpoints.mexc.domain.Dict.OpenType;
import com.g.fod.endpoints.mexc.domain.Dict.OrderSide;
import com.g.fod.endpoints.mexc.domain.Dict.OrderType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Resp;
import com.g.common.endpoints.core.rest.SymbolReq;
import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitReq;
import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitResp;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.Price;
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class OrderSubmitEndpoint extends AbstractRESTEndpoint<SubmitReq, SubmitResp> {

  public OrderSubmitEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v1/private/order/submit", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class SubmitResp extends Resp {

    private OrderId data;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class SubmitReq extends SymbolReq {

    public SubmitReq(String symbol) {
      super(symbol);
    }

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    private Price price = Price.ZERO; //	true	价格
    private int vol; //	true	数量
    private Integer leverage; //	false	杠杆倍数，逐仓时杠杆倍数必须传入
    private OrderSide side;
    private OrderType type = OrderType.LIMIT;
    private OpenType openType = OpenType.CROSS;
    private Long positionId; //	false	仓位id，平仓时建议传入该参数
    private String externalOid; //	false	外部订单号
    private BigDecimal stopLossPrice; //	false	止损价
    private BigDecimal takeProfitPrice; //	false	止盈价
    private boolean reduceOnly = false;

    public SubmitReq openLong() {return this.setSide(OrderSide.OPEN_LONG);}

    public SubmitReq closeLong() {return this.setSide(OrderSide.CLOSE_LONG);}

    public SubmitReq limit() {return this.setType(OrderType.LIMIT);}

    public SubmitReq market() {return this.setType(OrderType.MARKET);}
  }
}
