package com.p.quant.endpoints.okx.rest.trade;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.p.quant.endpoints.okx.rest.trade.BatchCancelOrderEndpoint.BatchCancelReq;
import com.p.quant.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelReq;
import com.p.quant.endpoints.okx.rest.trade.CancelOrderEndpoint.CancelResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class BatchCancelOrderEndpoint extends AbstractRESTEndpoint<BatchCancelReq, CancelResp> {

    public BatchCancelOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/trade/cancel-batch-orders", HttpMethod.POST);
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class BatchCancelReq extends Req implements List<CancelReq> {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        @Delegate
        @JsonIgnore
        private List<CancelReq> data;
    }
}
