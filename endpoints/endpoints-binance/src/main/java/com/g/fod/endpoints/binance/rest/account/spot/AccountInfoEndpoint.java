package com.g.fod.endpoints.binance.rest.account.spot;

import java.util.List;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

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
 */
public class AccountInfoEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public AccountInfoEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/account", HttpMethod.GET);
  }

  @Data
  public static class AccountInfoResp extends Resp {

    private int makerCommission;
    private int takerCommission;
    private int buyerCommission;
    private int sellerCommission;
    private boolean canTrade;
    private boolean canWithdraw;
    private boolean canDeposit;
    private long updateTime;
    private String accountType;
    private List<Balances> balances;
    private List<String> permissions;
  }

  @Data
  public static class Balances {

    private String asset;
    private String free;
    private String locked;
  }

  @Data
  public static class AccountInfoReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;

  }
}
