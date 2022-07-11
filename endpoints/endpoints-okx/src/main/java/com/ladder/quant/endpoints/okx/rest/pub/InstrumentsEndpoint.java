package com.ladder.quant.endpoints.okx.rest.pub;

import java.io.Serializable;
import java.util.List;

import com.ladder.quant.endpoints.okx.domain.Dict.InstType;
import com.ladder.quant.endpoints.okx.domain.InstId;
import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;

import com.p.common.base.json.JsonPrinter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class InstrumentsEndpoint extends
    AbstractRESTEndpoint<InstrumentsEndpoint.InstrumentsReq, InstrumentsEndpoint.InstrumentsResp> {

    public InstrumentsEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/public/instruments", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class InstrumentsResp extends Resp {

        private List<Data> data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        // 	产品类型
        private String instType;
        // 	产品id， 如 BTC-USD-SWAP
        private String instId;
        // 	标的指数，如 BTC-USD，仅适用于交割/永续/期权
        private String uly;
        // 	手续费档位，每个交易产品属于哪个档位手续费
        private String category;
        // 	交易货币币种，如 BTC-USDT 中的 BTC ，仅适用于币币
        private String baseCcy;
        // 	计价货币币种，如 BTC-USDT 中的USDT ，仅适用于币币
        private String quoteCcy;
        // 	盈亏结算和保证金币种，如 BTC 仅适用于交割/永续/期权
        private String settleCcy;
        // 	合约面值，仅适用于交割/永续/期权
        private String ctVal;
        // 	合约乘数，仅适用于交割/永续/期权
        private String ctMult;
        // 	合约面值计价币种，仅适用于交割/永续/期权
        private String ctValCcy;
        // 	期权类型，C或P 仅适用于期权
        private String optType;
        // 	行权价格，仅适用于期权
        private String stk;
        // 	上线日期 Unix时间戳的毫秒数格式，如 1597026383085
        private String listTime;
        // 	交割/行权日期，仅适用于交割 和 期权 Unix时间戳的毫秒数格式，如 1597026383085
        private String expTime;
        // 	该instId支持的最大杠杆倍数，不适用于币币、期权
        private String lever;
        // 	下单价格精度，如 0.0001
        private String tickSz;
        // 	下单数量精度，如 BTC-USDT-SWAP：1
        private String lotSz;
        // 	最小下单数量
        private String minSz;
        // 	linear：正向合约 inverse：反向合约 仅适用于交割/永续
        private String ctType;
        // 	合约日期别名 this_week：本周 next_week：次周 quarter：季度 next_quarter：次季度 仅适用于交割
        private String alias;
        // 	产品状态 live：交易中 suspend：暂停中 preopen：预上线 settlement：资金费结算
        private String state;
        // 	合约或现货限价单的单笔最大委托数量
        private String maxLmtSz;
        // 	合约或现货市价单的单笔最大委托数量
        private String maxMktSz;
        // 	合约或现货时间加权单的单笔最大委托数量
        private String maxTwapSz;
        // 	合约或现货冰山委托的单笔最大委托数量
        private String maxIcebergSz;
        // 	合约或现货计划委托委托的单笔最大委托数量
        private String maxTriggerSz;
        // 	合约或现货止盈止损委托的单笔最大委托数量
        private String maxStopSz;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class InstrumentsReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        private InstType instType;
        private String uly;
        private InstId instId;
    }
}
