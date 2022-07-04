package com.ladder.quant.endpoints.okx.rest.asset;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationReq;
import com.ladder.quant.endpoints.okx.rest.asset.AssetValuationEndpoint.AssetValuationResp;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class AssetValuationEndpoint extends AbstractRESTEndpoint<AssetValuationReq, AssetValuationResp> {

    public AssetValuationEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/asset-valuation", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class AssetValuationResp extends Resp {

        // 	账户总资产估值
        private String totalBal;
        // 	数据更新时间，Unix时间戳的毫秒数格式，如 1597026383085
        private String ts;
        private List<Detail> details;
    }

    public static class Detail implements Serializable {

        // 资金账户
        private String funding;
        // 交易账户
        private String trading;
        // 经典账户
        private String classic;
        // 金融账户
        private String earn;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class AssetValuationReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	否	资产估值对应的单位BTC 、USDT USD 、CNY 、JPY、KRW、RUB、EUR VND 、IDR 、INR、PHP、THB、TRY AUD 、SGD 、ARS、SAR、AED、IQD 默认为 BTC 为单位的估值
        private String ccy;
    }
}
