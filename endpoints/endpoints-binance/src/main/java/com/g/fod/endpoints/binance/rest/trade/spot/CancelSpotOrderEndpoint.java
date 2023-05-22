package com.g.fod.endpoints.binance.rest.trade.spot;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.enums.RequestType;
import com.g.fod.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderReq;
import com.g.fod.endpoints.binance.rest.trade.spot.CancelSpotOrderEndpoint.CancelSpotOrderResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 撤销订单
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class CancelSpotOrderEndpoint extends AbstractBinanceRESTEndpoint<CancelSpotOrderReq, CancelSpotOrderResp> {

  public CancelSpotOrderEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/order", HttpMethod.DELETE, RequestType.SIGNED);
  }

  @Data
  public static class CancelSpotOrderReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;
    private String symbol;//STRING	YES
    private Long orderId;//LONG	NO
    private String origClientOrderId;//	STRING	NO
    private String newClientOrderId;//	STRING	NO	用户自定义的本次撤销操作的ID(注意不是被撤销的订单的自定义ID)。如无指定会自动赋值。
  }

  @Data
  public static class CancelSpotOrderResp extends Resp {

    private String symbol;
    private String origClientOrderId;
    private Integer orderId;
    private Integer orderListId;
    private String clientOrderId;
    private String price;
    private String origQty;
    private String executedQty;
    private String cummulativeQuoteQty;
    private String status;
    private String timeInForce;
    private String type;
    private String side;
  }

}
