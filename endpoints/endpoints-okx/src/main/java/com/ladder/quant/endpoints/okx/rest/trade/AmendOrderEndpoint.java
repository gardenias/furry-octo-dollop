package com.ladder.quant.endpoints.okx.rest.trade;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderReq;
import com.ladder.quant.endpoints.okx.rest.trade.AmendOrderEndpoint.AmendOrderResp;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class AmendOrderEndpoint extends AbstractRESTEndpoint<AmendOrderReq, AmendOrderResp> {

    public AmendOrderEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/trade/amend-order", HttpMethod.POST);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class AmendOrderResp extends Resp {

        private Data data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        // 	订单ID
        private String ordId;
        // 	用户自定义ID
        private String clOrdId;
        // 	用户自定义修改事件ID
        private String reqId;
        // 	事件执行结果的code，0代表成功
        private String sCode;
        // 	事件执行失败时的msg
        private String sMsg;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class AmendOrderReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 		是	产品ID
        private String instId;
        // 		否	false：不自动撤单 true：自动撤单 当订单修改失败时，该订单是否需要自动撤销。默认为false
        private Boolean cxlOnFail;
        // 		可选	订单ID， ordId和clOrdId必须传一个，若传两个，以ordId为主
        private String ordId;
        // 		可选	用户自定义order ID
        private String clOrdId;
        // 		否	用户自定义修改事件ID 字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
        private String reqId;
        // 		可选	修改的新数量，newSz和newPx不可同时为空。对于部分成交订单，该数量应包含已成交数量。
        private String newSz;
        // 		可选	修改的新价格
        private String newPx;

    }

}
