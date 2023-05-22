package com.g.fod.endpoints.binance.rest.trade.spot;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.enums.RequestType;
import com.g.fod.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderReq;
import com.g.fod.endpoints.binance.rest.trade.spot.QuerySpotOrderEndpoint.QuerySpotOrderResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 查询订单
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/spot/cn/#user_data-18"
 */
public class QuerySpotOrderEndpoint extends AbstractBinanceRESTEndpoint<QuerySpotOrderReq, QuerySpotOrderResp> {

  public QuerySpotOrderEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/order", HttpMethod.GET, RequestType.SIGNED);
  }

  @Data
  public static class QuerySpotOrderReq extends BinanceReq {

    private String symbol;//	STRING	YES	交易对
    private Long orderId;//	LONG	NO	系统订单号
    private String origClientOrderId;//	STRING	NO	用户自定义的订单号
    private Long recvWindow;//	LONG	NO
    private Long timestamp;//	LONG	YES

  }

  @Data
  public static class QuerySpotOrderResp extends Resp {

    private String symbol;
    private int orderId;
    private int orderListId;
    private String clientOrderId;
    private String price;
    private String origQty;
    private String executedQty;
    private String cummulativeQuoteQty;
    private String status;
    private String timeInForce;
    private String type;
    private String side;
    private String stopPrice;
    private String icebergQty;
    private long time;
    private long updateTime;
    private boolean isWorking;
    private String origQuoteOrderQty;
  }

}
