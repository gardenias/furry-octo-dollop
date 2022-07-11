package com.ladder.quant.endpoints.okx.rest.account;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.account.LeverageInfoEndpoint.LeverageInfoReq;
import com.ladder.quant.endpoints.okx.rest.account.LeverageInfoEndpoint.LeverageInfoResp;
import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class LeverageInfoEndpoint extends AbstractRESTEndpoint<LeverageInfoReq, LeverageInfoResp> {

    public LeverageInfoEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/account/leverage-info", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class LeverageInfoResp extends Resp {

        private List<Data> data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        // 	产品ID
        private String instId;
        // 	保证金模式
        private String mgnMode;
        // 	持仓方向 long：双向持仓多头 short：双向持仓空头 net：单向持仓 双向持仓模式下会返回两个方向的杠杆倍数
        private String posSide;
        // 	杠杆倍数
        private String lever;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class LeverageInfoReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        //  	是	产品ID，如 BTC-USDT 支持多产品ID查询（不超过5个），半角逗号分隔
        private String instId;
        // 是	保证金模式isolated：逐仓 cross：全仓
        private String mgnMode;
    }
}
