package com.g.fod.endpoints.binance.rest.account.usdfutures;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 账户信息
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see <a href="https://binance-docs.github.io/apidocs/futures/cn/#v2-user_data">
 * https://binance-docs.github.io/apidocs/futures/cn/#v2-user_data</a>
 */
public class FuturesAccountBalanceV2Endpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public FuturesAccountBalanceV2Endpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, " /fapi/v2/balance", HttpMethod.GET);
  }

  /**
   *
   */
  @Data
  public static class AccountInfoResp extends Resp {

    private String accountAlias;
    private String asset;
    private String balance;
    private String crossWalletBalance;
    private String crossUnPnl;
    private String availableBalance;
    private String maxWithdrawAmount;
    private boolean marginAvailable;
    private long updateTime;
  }

  @Data
  public static class AccountInfoReq extends Req {

    private Long recvWindow;
    private Long timestamp;
  }
}
