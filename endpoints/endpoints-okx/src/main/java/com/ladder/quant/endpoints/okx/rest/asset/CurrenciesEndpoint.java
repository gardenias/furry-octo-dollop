package com.ladder.quant.endpoints.okx.rest.asset;

import java.util.List;

import com.ladder.quant.endpoints.okx.domain.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesReq;
import com.ladder.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesResp;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class CurrenciesEndpoint extends AbstractRESTEndpoint<CurrenciesReq, CurrenciesResp> {

    public CurrenciesEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/currencies", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class CurrenciesResp extends Resp {

        private List<Currency> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class CurrenciesReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }
    }
}
