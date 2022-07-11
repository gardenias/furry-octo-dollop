package com.ladder.quant.endpoints.okx.rest.pub;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class FundingRateHisEndpoint extends
    AbstractRESTEndpoint<FundingRateHisEndpoint.FundingRateHisReq, FundingRateHisEndpoint.FundingRateHisResp> {

    public FundingRateHisEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/public/funding-rate-history", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class FundingRateHisResp extends Resp {

        private List<Data> data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        // 	产品类型 SWAP：永续合约
        private String instType;
        // 	产品ID，如BTC-USD-SWAP
        private String instId;
        // 	资金费率
        private String fundingRate;
        // 	实际资金费率
        private String realizedRate;
        // 	资金费时间，Unix时间戳的毫秒数格式，如 1597026383085
        private String fundingTime;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class FundingRateHisReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        //	是	产品ID ，如 BTC-USD-SWAP 仅适用于永续
        private String instId;
        // 	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的fundingTime
        private String before;
        // 	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的fundingTime
        private String after;
        // 	否	分页返回的结果集数量，最大为100，不填默认返回100条
        private String limit;
    }
}
