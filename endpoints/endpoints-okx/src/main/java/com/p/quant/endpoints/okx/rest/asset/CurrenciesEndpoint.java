package com.p.quant.endpoints.okx.rest.asset;

import java.util.List;

import com.p.quant.endpoints.okx.domain.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesReq;
import com.p.quant.endpoints.okx.rest.asset.CurrenciesEndpoint.CurrenciesResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import com.p.quant.endpoints.rest.Resp;
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
