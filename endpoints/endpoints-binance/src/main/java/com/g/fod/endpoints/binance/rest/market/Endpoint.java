package com.g.fod.endpoints.binance.rest.market;

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
 * 账户信息
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class Endpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public Endpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "", HttpMethod.GET);
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
