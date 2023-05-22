package com.g.fod.endpoints.binance.rest.trade.usdfutures;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.enums.RequestType;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderReq;
import com.g.fod.endpoints.binance.rest.trade.usdfutures.QueryUsdFuturesOrderEndpoint.QueryUsdFuturesOrderResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 查询订单
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#trade-5"
 */
public class QueryUsdFuturesOrderEndpoint extends
  AbstractBinanceRESTEndpoint<QueryUsdFuturesOrderReq, QueryUsdFuturesOrderResp> {

  public QueryUsdFuturesOrderEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/order", HttpMethod.GET, RequestType.SIGNED);
  }

  @Data
  public static class QueryUsdFuturesOrderReq extends BinanceReq {

    private String symbol;//	STRING	YES	交易对
    private Long orderId;//	LONG	NO	系统订单号
    private String origClientOrderId;//	STRING	NO	用户自定义的订单号
    private Long recvWindow;//	LONG	NO
    private Long timestamp;//	LONG	YES

  }

  @Data
  public static class QueryUsdFuturesOrderResp extends Resp {

    private String avgPrice;
    private String clientOrderId;
    private String cumQuote;
    private String executedQty;
    private long orderId;
    private String origQty;
    private String origType;
    private String price;
    private boolean reduceOnly;
    private String side;
    private String positionSide;
    private String status;
    private String stopPrice;
    private boolean closePosition;
    private String symbol;
    private long time;
    private String timeInForce;
    private String type;
    private String activatePrice;
    private String priceRate;
    private long updateTime;
    private String workingType;
    private boolean priceProtect;
  }

}
