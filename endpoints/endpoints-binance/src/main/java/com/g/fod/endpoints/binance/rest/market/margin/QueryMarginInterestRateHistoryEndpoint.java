package com.g.fod.endpoints.binance.rest.market.margin;

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
 * 获取杠杆利率历史
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class QueryMarginInterestRateHistoryEndpoint extends
  AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public QueryMarginInterestRateHistoryEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/sapi/v1/margin/interestRateHistory", HttpMethod.GET);
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
