package com.ladder.quant.endpoints.okx.rest.account;

import java.util.List;

import com.ladder.quant.endpoints.okx.domain.Dict;
import com.ladder.quant.endpoints.okx.domain.InstId;
import com.ladder.quant.endpoints.okx.domain.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsReq;
import com.ladder.quant.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsResp;
import com.ladder.quant.endpoints.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.rest.HeadersProducer;
import com.ladder.quant.endpoints.rest.Req;
import com.ladder.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.common.base.json.JsonPrinter;

public class AccountPositionsEndpoint extends AbstractRESTEndpoint<AccountPositionsReq, AccountPositionsResp> {

    public AccountPositionsEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/account/positions", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class AccountPositionsResp extends Resp {

        private List<Position> data;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class AccountPositionsReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	否	产品类型MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权 instType和instId同时传入的时候会校验instId与instType是否一致。
        private Dict.InstType instType;
        // 	否	交易产品ID，如：BTC-USD-190927-5000-C 支持多个instId查询（不超过10个），半角逗号分隔
        private InstId instId;
        // 	否	持仓ID支持多个posId查询（不超过20个），半角逗号分割
        private String posId;
    }
}
