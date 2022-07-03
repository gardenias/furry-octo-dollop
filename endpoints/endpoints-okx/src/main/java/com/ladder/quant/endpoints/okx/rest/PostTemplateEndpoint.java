package com.ladder.quant.endpoints.okx.rest;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.PostTemplateEndpoint.PostTemplateReq;
import com.ladder.quant.endpoints.okx.rest.PostTemplateEndpoint.PostTemplateResp;
import com.ladder.quant.endpoints.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.rest.HeadersProducer;
import com.ladder.quant.endpoints.rest.Req;
import com.ladder.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.common.base.json.JsonPrinter;

class PostTemplateEndpoint extends AbstractRESTEndpoint<PostTemplateReq, PostTemplateResp> {

    public PostTemplateEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "TODO", HttpMethod.POST);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class PostTemplateResp extends Resp {

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
    public static class PostTemplateReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

    }
}