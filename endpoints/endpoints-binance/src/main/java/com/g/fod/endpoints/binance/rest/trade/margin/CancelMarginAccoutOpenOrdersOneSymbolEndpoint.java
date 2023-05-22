package com.g.fod.endpoints.binance.rest.trade.margin;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 杠杆账户撤销单一交易对的所有挂单
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class CancelMarginAccoutOpenOrdersOneSymbolEndpoint extends
  AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public CancelMarginAccoutOpenOrdersOneSymbolEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/sapi/v1/margin/openOrders", HttpMethod.GET);
  }

  @Data
  public static class AccountInfoReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;

  }

  @Data
  public static class AccountInfoResp extends Resp {

  }

}
