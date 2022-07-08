package com.p.quant.endpoints.binance.rest1.account.usdfutures;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest1.account.usdfutures.AccountPositonRiskEndpoint.AccountPositonRiskReq;
import com.p.quant.endpoints.binance.rest1.account.usdfutures.AccountPositonRiskEndpoint.AccountPositonRiskResp;

import lombok.Data;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * 用户持仓风险V2 
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#trade-13"
 * @date  ：Created in 2022/7/7
 */
public class AccountPositonRiskEndpoint extends AbstractBinanceRESTEndpoint<AccountPositonRiskReq, AccountPositonRiskResp> {

    public AccountPositonRiskEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v2/positionRisk", HttpMethod.GET);
    }
    @Data
    public static class AccountPositonRiskReq extends BinanceReq {
        private Long recvWindow;
        private Long timestamp;
        private String symbol;

    }
    @Data
    public static class AccountPositonRiskResp extends Resp {

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
   
}
