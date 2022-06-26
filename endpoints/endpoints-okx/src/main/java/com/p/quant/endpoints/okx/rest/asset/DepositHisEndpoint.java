package com.p.quant.endpoints.okx.rest.asset;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisReq;
import com.p.quant.endpoints.okx.rest.asset.DepositHisEndpoint.DepositHisResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import com.p.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class DepositHisEndpoint extends AbstractRESTEndpoint<DepositHisReq, DepositHisResp> {

    public DepositHisEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/deposit-address", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class DepositHisResp extends Resp {

        private List<Data> data;
    }

    public static class Data implements Serializable {

        // 		币种名称，如 BTC
        private String ccy;
        // 		币种链信息 有的币种下有多个链，必须要做区分，如USDT下有USDT-ERC20，USDT-TRC20，USDT-Omni多个链
        private String chain;
        // 		充值数量
        private String amt;
        // 	充值地址，只显示内部账户转账地址，不显示区块链充值地址
        private String from;
        // 	到账地址
        private String to;
        // 	区块转账哈希记录
        private String txId;
        // 	充值到账时间，Unix 时间戳的毫秒数格式，如 1597026383085
        private String ts;
        // 		充值状态 0：等待确认 1：确认到账 2：充值成功 8：因该币种暂停充值而未到账，恢复充值后自动到账 12：账户或充值被冻结 13：子账户充值拦截
        private String state;
        // 		充值记录 ID
        private String depId;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class DepositHisReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	否	币种名称，如 BTC
        private String ccy;
        // 	否	充值记录 ID
        private String depId;
        // 	否	区块转账哈希记录
        private String txId;
        // 	否	充值状态 0：等待确认 1：确认到账 2：充值成功
        private String state;
        // 	否	查询在此之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085
        private String after;
        // 	否	查询在此之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085
        private String before;
        // 	否	返回的结果集数量，默认为100，最大为100，不填默认返回100条
        private String limit;
    }
}
