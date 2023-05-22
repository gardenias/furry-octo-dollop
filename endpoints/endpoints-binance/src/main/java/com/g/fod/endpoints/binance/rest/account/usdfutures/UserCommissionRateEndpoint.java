package com.g.fod.endpoints.binance.rest.account.usdfutures;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.account.usdfutures.UserCommissionRateEndpoint.UserCommissionRateReq;
import com.g.fod.endpoints.binance.rest.account.usdfutures.UserCommissionRateEndpoint.UserCommissionRateResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 用户手续费率 (USER_DATA)
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#user_data-12"
 */
public class UserCommissionRateEndpoint extends
  AbstractBinanceRESTEndpoint<UserCommissionRateReq, UserCommissionRateResp> {

  public UserCommissionRateEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/commissionRate", HttpMethod.POST);
  }

  @Data
  public static class UserCommissionRateReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;
    private String symbol;

  }

  @Data
  public static class UserCommissionRateResp extends Resp {

    private String symbol;
    private String makerCommissionRate;
    private String takerCommissionRate;
  }

}
