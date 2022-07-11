package com.ladder.quant.endpoints.okx.rest.account;

import java.io.Serializable;
import java.util.List;

import com.ladder.quant.endpoints.okx.domain.Dict.InstType;

import com.ladder.quant.endpoints.okx.domain.InstId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.account.AccountPositionRiskEndpoint.AccountPositionRiskReq;
import com.ladder.quant.endpoints.okx.rest.account.AccountPositionRiskEndpoint.AccountPositionRiskResp;
import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class AccountPositionRiskEndpoint extends AbstractRESTEndpoint<AccountPositionRiskReq, AccountPositionRiskResp> {

    public AccountPositionRiskEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/account/account-position-risk", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class AccountPositionRiskResp extends Resp {

        // 	获取账户信息数据的时间，Unix时间戳的毫秒数格式，如 1597026383085
        private String ts;
        // 	美金层面有效保证金 适用于跨币种保证金模式 和组合保证金模式
        private String adjEq;

        // 	币种资产信息
        private List<BalData> balData;
        // 持仓详细信息
        private List<PosData> posData;
    }

    @Setter
    @Getter
    public static class BalData implements Serializable {

        // 	币种
        private String ccy;
        // 	币种总权益
        private String eq;
        // 	美金层面币种折算权益
        private String disEq;
    }

    @Setter
    @Getter
    public static class PosData implements Serializable {

        // 产品类型
        private String instType;
        // 保证金模式 cross：全仓 isolated：逐仓
        private String mgnMode;
        // 持仓ID
        private String posId;
        // 产品ID，如 BTC-USD-180216
        private String instId;
        // 以张为单位的持仓数量，逐仓自主划转模式下，转入保证金后会产生pos为0的仓位
        private String pos;
        // 交易币余额，适用于 币币杠杆（逐仓自主划转模式）
        private String baseBal;
        // 计价币余额 ，适用于 币币杠杆（逐仓自主划转模式）
        private String quoteBal;
        // 持仓方向 long：双向持仓多头 short：双向持仓空头 net：单向持仓（交割/永续/期权：pos为正代表多头，pos为负代表空头。币币杠杆：posCcy为交易货币时，代表多头；posCcy为计价货币时，代表空头。）
        private String posSide;
        // 仓位资产币种，仅适用于币币杠杆仓位
        private String posCcy;
        // 占用保证金的币种
        private String ccy;
        // 以币为单位的持仓数量
        private String notionalCcy;
        // 以美金价值为单位的持仓数量
        private String notionalUsd;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class AccountPositionRiskReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	否	产品类型MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权 instType和instId同时传入的时候会校验instId与instType是否一致。
        private InstType instType;
        private InstId instId;
    }
}
