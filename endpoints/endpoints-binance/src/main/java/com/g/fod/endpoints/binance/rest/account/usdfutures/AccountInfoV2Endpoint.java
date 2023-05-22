package com.g.fod.endpoints.binance.rest.account.usdfutures;

import java.util.List;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.account.usdfutures.AccountInfoV2Endpoint.AccountInfoV2Req;
import com.g.fod.endpoints.binance.rest.account.usdfutures.AccountInfoV2Endpoint.AccountInfoV2Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 账户信息V2
 *
 * @author：wanghao
 * @date ：reated in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#v2-user_data-2"
 */
public class AccountInfoV2Endpoint extends AbstractBinanceRESTEndpoint<AccountInfoV2Req, AccountInfoV2Resp> {

  public AccountInfoV2Endpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v2/account", HttpMethod.GET);
  }

  @Data
  public static class AccountInfoV2Resp extends Resp {

    private int feeTier;
    private boolean canTrade;
    private boolean canDeposit;
    private boolean canWithdraw;
    private int updateTime;
    private String totalInitialMargin;
    private String totalMaintMargin;
    private String totalWalletBalance;
    private String totalUnrealizedProfit;
    private String totalMarginBalance;
    private String totalPositionInitialMargin;
    private String totalOpenOrderInitialMargin;
    private String totalCrossWalletBalance;
    private String totalCrossUnPnl;
    private String availableBalance;
    private String maxWithdrawAmount;
    private List<Assets> assets;
    private List<Positions> positions;
  }

  @Data
  class Assets {

    private String asset;
    private String walletBalance;
    private String unrealizedProfit;
    private String marginBalance;
    private String maintMargin;
    private String initialMargin;
    private String positionInitialMargin;
    private String openOrderInitialMargin;
    private String crossWalletBalance;
    private String crossUnPnl;
    private String availableBalance;
    private String maxWithdrawAmount;
    private boolean marginAvailable;
    private long updateTime;
  }

  @Data
  class Positions {

    private String symbol;
    private String initialMargin;
    private String maintMargin;
    private String unrealizedProfit;
    private String positionInitialMargin;
    private String openOrderInitialMargin;
    private String leverage;
    private boolean isolated;
    private String entryPrice;
    private String maxNotional;
    private String bidNotional;
    private String askNotional;
    private String positionSide;
    private String positionAmt;
    private int updateTime;
  }

  @Data
  public static class AccountInfoV2Req extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;
  }
}
