package com.g.fod.endpoints.binance.rest.trade.spot;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.enums.RequestType;
import com.g.fod.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderReq;
import com.g.fod.endpoints.binance.rest.trade.spot.PlaceSpotOrderEndpoint.PlaceSpotOrderResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 下单
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/spot/cn/#trade-2"
 */
public class PlaceSpotOrderEndpoint extends AbstractBinanceRESTEndpoint<PlaceSpotOrderReq, PlaceSpotOrderResp> {

  public PlaceSpotOrderEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/order", HttpMethod.POST, RequestType.SIGNED);
  }

  @Data
  public static class PlaceSpotOrderReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;
    private String symbol;  //STRING	YES
    private String side;  //	ENUM	YES	详见枚举定义：订单方向
    private String type;  //	ENUM	YES	详见枚举定义：订单类型
    private String timeInForce;  //	ENUM	NO	详见枚举定义：有效方式
    private String quantity;  //	DECIMAL	NO
    private BigDecimal quoteOrderQty;  //	DECIMAL	NO
    private BigDecimal price;  //	DECIMAL	NO
    private String newClientOrderId;  //	STRING	NO	客户自定义的唯一订单ID。 如果未发送，则自动生成
    private BigDecimal stopPrice;  //	DECIMAL	NO	仅 STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, 和TAKE_PROFIT_LIMIT 需要此参数。
    private Long trailingDelta;  //	LONG	NO	用于 STOP_LOSS, STOP_LOSS_LIMIT, TAKE_PROFIT, 和 TAKE_PROFIT_LIMIT 类型的订单. 更多追踪止盈止损订单细节, 请参考 追踪止盈止损(Trailing Stop)订单常见问题
    private BigDecimal icebergQty;  //	DECIMAL	NO	仅使用 LIMIT, STOP_LOSS_LIMIT, 和 TAKE_PROFIT_LIMIT 创建新的 iceberg 订单时需要此参数
    private String newOrderRespType;  //	ENUM	NO	设置响应JSON。 ACK，RESULT或FULL； "MARKET"和" LIMIT"订单类型默认为"FULL"，所有其他订单默认为"ACK"。
  }

  @Data
  public static class PlaceSpotOrderResp extends Resp {

    private String symbol;
    private int orderId;
    private int orderListId;
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
    private List<Fills> fills;
  }

  @Data
  public static class Fills {

    private String price;
    private String qty;
    private String commission;
    private String commissionAsset;
    private int tradeId;
  }

}
