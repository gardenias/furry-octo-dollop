package com.p.quant.endpoints.okx.rest;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.quant.endpoints.okx.rest.GetTemplateEndpoint.GetTemplateReq;
import com.p.quant.endpoints.okx.rest.GetTemplateEndpoint.GetTemplateResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import com.p.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

class GetTemplateEndpoint extends AbstractRESTEndpoint<GetTemplateReq, GetTemplateResp> {

    public GetTemplateEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "TODO", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class GetTemplateResp extends Resp {

        private List<Data> data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class GetTemplateReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

    }
}
