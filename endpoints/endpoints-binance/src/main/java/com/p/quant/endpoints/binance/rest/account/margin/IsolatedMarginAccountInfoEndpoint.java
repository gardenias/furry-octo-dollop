package com.p.quant.endpoints.binance.rest.account.margin;

import java.util.List;

import com.p.quant.endpoints.binance.rest.account.margin.IsolatedMarginAccountInfoEndpoint.IsolatedMarginAccountInfoReq;
import com.p.quant.endpoints.binance.rest.account.margin.IsolatedMarginAccountInfoEndpoint.IsolatedMarginAccountInfoResp;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * 查询全仓杠杆账户详情
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/spot/cn/#user_data-35"
 * @date  ：Created in 2022/7/7
 */
public class IsolatedMarginAccountInfoEndpoint extends AbstractBinanceRESTEndpoint<IsolatedMarginAccountInfoReq, IsolatedMarginAccountInfoResp> {

    public IsolatedMarginAccountInfoEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/sapi/v1/margin/isolated/account", HttpMethod.GET);
    }

    @Data
    public static class IsolatedMarginAccountInfoResp extends Resp {
        private List<Assets> assets;
    }
    @Data
    class Assets {

        private BaseAsset baseAsset;
        private QuoteAsset quoteAsset;
        private String symbol;
        private boolean isolatedCreated;
        private boolean enabled;
        private String marginLevel;
        private String marginLevelStatus;
        private String marginRatio;
        private String indexPrice;
        private String liquidatePrice;
        private String liquidateRate;
        private boolean tradeEnabled;
    }
    @Data
    class QuoteAsset {

        private String asset;
        private boolean borrowEnabled;
        private String borrowed;
        private String free;
        private String interest;
        private String locked;
        private String netAsset;
        private String netAssetOfBtc;
        private boolean repayEnabled;
        private String totalAsset;
    }
    @Data
    class BaseAsset {

        private String asset;
        private boolean borrowEnabled;
        private String borrowed;
        private String free;
        private String interest;
        private String locked;
        private String netAsset;
        private String netAssetOfBtc;
        private boolean repayEnabled;
        private String totalAsset;
    }

    @Data
    public static class IsolatedMarginAccountInfoReq extends BinanceReq {

        private String symbols;
        private Long recvWindow;
        private Long timestamp;
    }
}
