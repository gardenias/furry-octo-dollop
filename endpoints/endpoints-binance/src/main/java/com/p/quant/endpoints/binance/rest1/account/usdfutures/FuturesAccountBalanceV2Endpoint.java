package com.p.quant.endpoints.binance.rest1.account.usdfutures;

import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest1.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.p.quant.endpoints.binance.rest1.account.spot.AccountInfoEndpoint.AccountInfoResp;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 账户信息
 * @author：wanghao
 *  @see <a href="https://binance-docs.github.io/apidocs/futures/cn/#v2-user_data"> https://binance-docs.github.io/apidocs/futures/cn/#v2-user_data</a>
 * @date  ：Created in 2022/7/7
 */
public class FuturesAccountBalanceV2Endpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

    public FuturesAccountBalanceV2Endpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, " /fapi/v2/balance", HttpMethod.GET);
    }

    /**
     
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
