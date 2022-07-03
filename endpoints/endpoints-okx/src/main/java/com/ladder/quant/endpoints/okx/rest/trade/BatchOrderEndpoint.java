package com.ladder.quant.endpoints.okx.rest.trade;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ladder.quant.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.BatchOrderEndpoint.BatchOrderResp;
import com.ladder.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.PlaceOrderEndpoint.PlaceOrderRespData;
import com.ladder.quant.endpoints.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.rest.HeadersProducer;
import com.ladder.quant.endpoints.rest.Req;
import com.ladder.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class BatchOrderEndpoint extends AbstractRESTEndpoint<BatchOrderReq, BatchOrderResp> {

    public BatchOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/trade/batch-orders", HttpMethod.POST);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class BatchOrderResp extends Resp {

        private List<PlaceOrderRespData> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class BatchOrderReq extends Req implements List<PlaceOrderReq> {

        @Delegate
        @JsonIgnore
        private List<PlaceOrderReq> data;

    }

}
