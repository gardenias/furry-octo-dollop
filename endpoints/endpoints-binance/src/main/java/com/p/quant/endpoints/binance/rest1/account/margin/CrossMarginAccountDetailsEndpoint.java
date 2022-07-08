package com.p.quant.endpoints.binance.rest1.account.margin;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest1.account.margin.CrossMarginAccountDetailsEndpoint.CrossMarginAccountDetailsReq;
import com.p.quant.endpoints.binance.rest1.account.margin.CrossMarginAccountDetailsEndpoint.CrossMarginAccountDetailsResp;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


/**
 * 查询全仓杠杆账户详情
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/spot/cn/#user_data-27"
 * @date  ：Created in 2022/7/7
 */
public class CrossMarginAccountDetailsEndpoint extends AbstractBinanceRESTEndpoint<CrossMarginAccountDetailsReq, CrossMarginAccountDetailsResp> {

    public CrossMarginAccountDetailsEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/sapi/v1/margin/account", HttpMethod.GET);
    }

    @Data
    public static class CrossMarginAccountDetailsResp extends Resp {
        private boolean borrowEnabled;
        private String marginLevel;
        private String totalAssetOfBtc;
        private String totalLiabilityOfBtc;
        private String totalNetAssetOfBtc;
        private boolean tradeEnabled;
        private boolean transferEnabled;
        private List<UserAssets> userAssets;
    }
    @Data
     class UserAssets {

        private String asset;
        private String borrowed;
        private String free;
        private String interest;
        private String locked;
        private String netAsset;
    }
    @Data
    public static class CrossMarginAccountDetailsReq extends BinanceReq {

        private Long recvWindow;
        private Long timestamp;
    }
}
