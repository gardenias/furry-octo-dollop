package com.g.fod.endpoints.binance.rest.trade.margin;

import java.util.List;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.enums.RequestType;
import com.g.fod.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderReq;
import com.g.fod.endpoints.binance.rest.trade.margin.PlaceMarginAccountOrderEndpoint.PlaceMarginAccountOrderResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 杠杆账户下单
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class PlaceMarginAccountOrderEndpoint extends
  AbstractBinanceRESTEndpoint<PlaceMarginAccountOrderReq, PlaceMarginAccountOrderResp> {

  public PlaceMarginAccountOrderEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/sapi/v1/margin/order", HttpMethod.POST, RequestType.SIGNED);
  }

  @Data
  public static class PlaceMarginAccountOrderReq extends BinanceReq {

    private String symbol;//	STRING	YES
    private String isIsolated;//	STRING	NO	是否逐仓杠杆，"TRUE", "FALSE", 默认 "FALSE"
    private String side;//	ENUM	YES	BUY SELL
    private String type;//	ENUM	YES	详见枚举定义：订单类型
    private String quantity;//	DECIMAL	NO
    private String quoteOrderQty;//	DECIMAL	NO
    private String price;//	DECIMAL	NO
    private String stopPrice;//	DECIMAL	NO	与STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, 和 TAKE_PROFIT_LIMIT 订单一起使用.
    private String newClientOrderId;//	STRING	NO	客户自定义的唯一订单ID。若未发送自动生成。
    private String icebergQty;//	DECIMAL	NO	与 LIMIT, STOP_LOSS_LIMIT, 和 TAKE_PROFIT_LIMIT 一起使用创建 iceberg 订单.
    private String newOrderRespType;//	ENUM	NO	设置响应: JSON. ACK, RESULT, 或 FULL; MARKET 和 LIMIT 订单类型默认为 FULL, 所有其他订单默认为 ACK.
    private String sideEffectType;//	ENUM	NO	NO_SIDE_EFFECT, MARGIN_BUY, AUTO_REPAY;默认为 NO_SIDE_EFFECT.
    private String timeInForce;//	ENUM	NO	GTC,IOC,FOK
    private Long recvWindow;//	LONG	NO	赋值不能大于 60000
    private Long timestamp;//	LONG	YES
  }

  @Data
  public static class PlaceMarginAccountOrderResp extends Resp {

    private String symbol;
    private int orderId;
    private String clientOrderId;
    private long transactTime;
    private String price;
    private String origQty;
    private String executedQty;
    private String cummulativeQuoteQty;
    private String status;
    private String timeInForce;
    private String type;
    private String side;
    private int marginBuyBorrowAmount;
    private String marginBuyBorrowAsset;
    private boolean isIsolated;
    private List<Fills> fills;
  }

  @Data
  public static class Fills {

    private String price;
    private String qty;
    private String commission;
    private String commissionAsset;
  }
}
