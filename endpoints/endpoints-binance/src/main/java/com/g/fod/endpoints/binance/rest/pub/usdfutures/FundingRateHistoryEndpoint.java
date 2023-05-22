package com.g.fod.endpoints.binance.rest.pub.usdfutures;

import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 查询资金费率历史
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class FundingRateHistoryEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public FundingRateHistoryEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/fundingRate", HttpMethod.GET);
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
