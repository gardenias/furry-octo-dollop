package com.ladder.quant.endpoints.okx.rest.asset;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateReq;
import com.ladder.quant.endpoints.okx.rest.asset.TransferStateEndpoint.TransferStateResp;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class TransferStateEndpoint extends AbstractRESTEndpoint<TransferStateReq, TransferStateResp> {

    public TransferStateEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/transfer-state", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class TransferStateResp extends Resp {

        private List<Data> data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        // 0：账户内划转 1：母账户转子账户 2：子账户转母账户
        private String type;
        // 划转 ID
        private String transId;
        // 客户自定义ID
        private String clientId;
        // 划转币种
        private String ccy;
        // 转出账户
        private String from;
        // 划转量
        private String amt;
        // 转入账户
        private String to;
        // 子账户名称
        private String subAcct;
        // 币币杠杆转出币对（如 BTC-USDT）或者转出合约的 underlying（如 BTC-USD）
        private String instId;
        // 币币杠杆转入币对（如 BTC-USDT）或者转入合约的 underlying（如 BTC-USD）
        private String toInstId;
        // 转账状态 成功：success，处理中：pending，失败：failed
        private String state;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class TransferStateReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	可选	划转IDtransId和clientId必须传一个，若传两个，以transId为主
        private String transId;
        // 	可选	客户自定义ID
        private String clientId;

        /**
         * <pre>
         * 划转类型
         * 0：账户内划转
         * 1：母账户转子账户(仅适用于母账户APIKey)
         * 2：子账户转母账户(仅适用于母账户APIKey)
         * 3：子账户转母账户(仅适用于子账户APIKey)
         * 4：子账户转子账户(仅适用于子账户APIKey，且目标账户需要是同一母账户下的其他子账户)
         * </pre>
         */
        private String type;
    }
}
