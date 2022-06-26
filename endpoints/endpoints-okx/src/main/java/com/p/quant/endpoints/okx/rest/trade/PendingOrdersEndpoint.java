package com.p.quant.endpoints.okx.rest.trade;

import java.util.List;

import com.p.quant.endpoints.okx.domain.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.quant.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsReq;
import com.p.quant.endpoints.okx.rest.trade.PendingOrdersEndpoint.PendingOrdsResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import com.p.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class PendingOrdersEndpoint extends AbstractRESTEndpoint<PendingOrdsReq, PendingOrdsResp> {

    public PendingOrdersEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/trade/orders-pending", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class PendingOrdsResp extends Resp {

        private List<Order> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class PendingOrdsReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	否	产品类型 SPOT：币币 MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        private String instType;
        // 	否	标的指数
        private String uly;
        // 	否	产品ID，如BTC-USD-200927
        private String instId;
        // 	否	订单类型 market：市价单 limit：限价单 post_only：只做maker单 fok：全部成交或立即取消 ioc：立即成交并取消剩余 optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续）
        private String ordType;
        // 	否	订单状态 live：等待成交 partially_filled：部分成交
        private String state;
        // 	否	请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的ordId
        private String after;
        // 	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的ordId
        private String before;
        // 	否	返回结果的数量，默认100条
        private String limit;

    }
}
