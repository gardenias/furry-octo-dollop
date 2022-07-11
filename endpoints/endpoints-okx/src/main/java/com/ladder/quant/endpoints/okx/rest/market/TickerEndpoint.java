package com.ladder.quant.endpoints.okx.rest.market;

import java.util.List;

import com.ladder.quant.endpoints.okx.domain.Ticker;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ladder.quant.endpoints.okx.rest.market.TickerEndpoint.TickerReq;
import com.ladder.quant.endpoints.okx.rest.market.TickerEndpoint.TickerResp;
import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class TickerEndpoint extends AbstractRESTEndpoint<TickerReq, TickerResp> {

    public TickerEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/market/ticker", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class TickerResp extends Resp {

        @Delegate
        @JsonIgnore
        private List<Ticker> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class TickerReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	是	产品ID，如 BTC-USD-SWAP
        private String instId;
    }
}
