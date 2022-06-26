package com.p.quant.endpoints.okx.rest.trade;

import java.util.List;

import com.p.quant.endpoints.okx.domain.Fill;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.quant.endpoints.okx.rest.trade.FillsEndpoint.FillsReq;
import com.p.quant.endpoints.okx.rest.trade.FillsEndpoint.FillsResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import com.p.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class FillsEndpoint extends AbstractRESTEndpoint<FillsReq, FillsResp> {

    public FillsEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/trade/fills", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class FillsResp extends Resp {

        private List<Fill> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class FillsReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	是	产品类型 SPOT：币币 MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        private String instType;
        // 		否	标的指数
        private String uly;
        // 	否	产品 ID，如BTC-USD-190927
        private String instId;
        // 	否	订单 ID
        private String ordId;
        // 	否	请求此 ID 之前（更旧的数据）的分页内容，传的值为对应接口的billId
        private String after;
        // 	否	请求此 ID 之后（更新的数据）的分页内容，传的值为对应接口的billId
        private String before;
        // 	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085
        private String begin;
        // 	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085
        private String end;
        // 	否	返回结果的数量，默认 100 条
        private String limit;
    }
}
