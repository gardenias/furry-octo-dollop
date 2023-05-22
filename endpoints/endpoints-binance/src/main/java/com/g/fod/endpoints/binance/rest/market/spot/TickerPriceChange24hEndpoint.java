package com.g.fod.endpoints.binance.rest.market.spot;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 24hr 价格变动情况
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class TickerPriceChange24hEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public TickerPriceChange24hEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/ticker/24hr", HttpMethod.GET);
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
