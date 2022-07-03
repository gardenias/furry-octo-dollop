package com.ladder.quant.endpoints.okx.rest.asset;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.domain.Currency;
import com.ladder.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesReq;
import com.ladder.quant.endpoints.okx.rest.asset.AssetBalancesEndpoint.BalancesResp;
import com.ladder.quant.endpoints.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.rest.HeadersProducer;
import com.ladder.quant.endpoints.rest.Req;
import com.ladder.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.common.base.json.JsonPrinter;

public class AssetBalancesEndpoint extends AbstractRESTEndpoint<BalancesReq, BalancesResp> {

    public AssetBalancesEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/balances", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class BalancesResp extends Resp {

        private List<Currency> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class BalancesReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }
    }
}
