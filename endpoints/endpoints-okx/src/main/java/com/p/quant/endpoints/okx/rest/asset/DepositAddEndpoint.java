package com.p.quant.endpoints.okx.rest.asset;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.quant.endpoints.okx.domain.DepositAdd;
import com.p.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddReq;
import com.p.quant.endpoints.okx.rest.asset.DepositAddEndpoint.DepositAddResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import com.p.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class DepositAddEndpoint extends AbstractRESTEndpoint<DepositAddReq, DepositAddResp> {

    public DepositAddEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/deposit-address", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class DepositAddResp extends Resp {

        private List<DepositAdd> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class DepositAddReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 是	币种，如BTC
        private String ccy;
    }
}
