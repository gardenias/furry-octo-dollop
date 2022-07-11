package com.ladder.quant.endpoints.okx.rest.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.account.SetPositionModeEndpoint.SetPositionModeReq;
import com.ladder.quant.endpoints.okx.rest.account.SetPositionModeEndpoint.SetPositionModeResp;
import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class SetPositionModeEndpoint extends AbstractRESTEndpoint<SetPositionModeReq, SetPositionModeResp> {

    public SetPositionModeEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/account/set-position-mode", HttpMethod.POST);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class SetPositionModeResp extends Resp {

        // 	是	持仓方式long_short_mode：双向持仓 net_mode：单向持仓 仅适用交割/永续
        private String posMode;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class SetPositionModeReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	是	持仓方式long_short_mode：双向持仓 net_mode：单向持仓 仅适用交割/永续
        private String posMode;
    }
}
