package com.ladder.quant.endpoints.okx.rest.trade;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Resp;
import com.ladder.quant.endpoints.core.rest.SymbolReq;
import com.ladder.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderResp;

public class PlaceOrderEndpoint extends AbstractRESTEndpoint<PlaceOrderReq, PlaceOrderResp> {

  public PlaceOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/trade/order", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class PlaceOrderResp extends Resp {

    private PlaceOrderRespData data;
  }

  @Setter
  @Getter
  public static class PlaceOrderRespData implements Serializable {

    //	订单ID
    private String ordId;
    //	客户自定义订单ID
    private String clOrdId;
    //	订单标签
    private String tag;
    //	事件执行结果的code，0代表成功
    private String sCode;
    //	事件执行失败时的msg
    private String sMsg;

  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class PlaceOrderReq extends SymbolReq {

    public PlaceOrderReq(String symbol) {
      super(symbol);
    }

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    //	是	产品ID，如 BTC-USD-190927-5000-C
    private String instId;
    //	是	交易模式 保证金模式：isolated：逐仓 ；cross：全仓 非保证金模式：cash：非保证金
    private String tdMode;
    //	否	保证金币种，仅适用于单币种保证金模式下的全仓杠杆订单
    private String ccy;
    //	否	客户自定义订单ID 字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
    private String clOrdId;
    //	否	订单标签 字母（区分大小写）与数字的组合，可以是纯字母、纯数字，且长度在1-16位之间。
    private String tag;
    //	是	订单方向 buy：买 sell：卖
    private String side;
    //	可选	持仓方向 在双向持仓模式下必填，且仅可选择 long 或 short
    private String posSide;
    //	是	订单类型 market：市价单 limit：限价单 post_only：只做maker单 fok：全部成交或立即取消 ioc：立即成交并取消剩余 optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续）
    private String ordType;
    //	是	委托数量
    private String sz;
    //	可选	委托价格，仅适用于limit、post_only、fok、ioc类型的订单
    private String px;
    //	否	是否只减仓，true 或 false，默认false 仅适用于币币杠杆，以及买卖模式下的交割/永续 仅适用于单币种保证金模式和跨币种保证金模式
    private Boolean reduceOnly;
    //	否	市价单委托数量的类型 base_ccy: 交易货币 ；quote_ccy：计价货币 仅适用于币币订单
    private String tgtCcy;
    //	否	是否禁止币币市价改单，true 或 false，默认false 为true时，余额不足时会下单失败，仅适用于币币市价单
    private Boolean banAmend;

  }

}
