package com.ladder.quant.endpoints.okx.rest.trade;

import java.util.List;

import com.ladder.quant.endpoints.okx.domain.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.trade.GetOrderEndpoint.OrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.GetOrderEndpoint.OrderResp;
import com.ladder.quant.endpoints.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.rest.HeadersProducer;
import com.ladder.quant.endpoints.rest.Req;
import com.ladder.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.common.base.json.JsonPrinter;

public class GetOrderEndpoint extends AbstractRESTEndpoint<OrderReq, OrderResp> {

    public GetOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/trade/order", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class OrderResp extends Resp {

        private List<Order> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class OrderReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        //	是	产品ID ，如BTC-USD-190927
        private String instId;
        //	可选	订单ID ， ordId和clOrdId必须传一个，若传两个，以ordId为主
        private String ordId;
        //	可选	用户自定义ID
        private String clOrdId;

    }

}
