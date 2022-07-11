package com.ladder.quant.endpoints.okx.rest.trade;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.trade.ClosePositionEndpoint.ClosePositionReq;
import com.ladder.quant.endpoints.okx.rest.trade.ClosePositionEndpoint.ClosePositionResp;
import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class ClosePositionEndpoint extends AbstractRESTEndpoint<ClosePositionReq, ClosePositionResp> {

    public ClosePositionEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/trade/close-position", HttpMethod.POST);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class ClosePositionResp extends Resp {

        private Data data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        //	产品ID
        private String instId;
        //	持仓方向
        private String posSide;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class ClosePositionReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 		是	产品ID
        private String instId;
        // 		可选	持仓方向单向持仓模式下：可不填写此参数，默认值net，如果填写，仅可以填写net 双向持仓模式下： 必须填写此参数，且仅可以填写 long：平多 ，short：平空
        private String posSide;
        // 		是	保证金模式cross：全仓 ； isolated：逐仓
        private String mgnMode;
        // 		可选	保证金币种，单币种保证金模式的全仓币币杠杆平仓必填
        private String ccy;
        // 		否	当市价全平时，平仓单是否需要自动撤销,默认为false.false：不自动撤单 true：自动撤单
        private Boolean autoCxl;

    }

}
