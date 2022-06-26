package com.p.quant.endpoints.okx.rest.asset;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisReq;
import com.p.quant.endpoints.okx.rest.asset.WithdrawalHisEndpoint.WithdrawalHisResp;
import com.p.quant.endpoints.rest.AbstractRESTEndpoint;
import com.p.quant.endpoints.rest.HeadersProducer;
import com.p.quant.endpoints.rest.Req;
import com.p.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class WithdrawalHisEndpoint extends AbstractRESTEndpoint<WithdrawalHisReq, WithdrawalHisResp> {

    public WithdrawalHisEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/withdrawal-history", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class WithdrawalHisResp extends Resp {

        private List<Data> data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        //  	币种
        private String ccy;
        //  	币种链信息 有的币种下有多个链，必须要做区分，如USDT下有USDT-ERC20，USDT-TRC20，USDT-Omni多个链
        private String chain;
        //  	数量
        private String amt;
        //  	提币申请时间，Unix 时间戳的毫秒数格式，如 1597026383085
        private String ts;
        //  	提币地址（如果收币地址是 OKX 平台地址，则此处将显示用户账户）
        private String from;
        //  	收币地址
        private String to;
        //  	部分币种提币需要标签，若不需要则不返回此字段
        private String tag;
        //  	部分币种提币需要此字段（payment_id），若不需要则不返回此字段
        private String pmtId;
        //  	部分币种提币需要此字段，若不需要则不返回此字段
        private String memo;
        //  	提币哈希记录（内部转账将不返回此字段）
        private String txId;
        //  	提币手续费
        private String fee;
        //  	提币状态-3：撤销中 -2：已撤销 -1：失败 0：等待提现 1：提现中 2：已汇出 3：邮箱确认 4：人工审核中 5：等待身份认证
        private String state;
        //  	提币申请ID
        private String wdId;
        //  	客户自定义ID
        private String clientId;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class WithdrawalHisReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	否	币种名称，如 BTC
        private String ccy;
        // 	否	提币申请ID
        private String wdId;
        // 	否	客户自定义ID字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
        private String clientId;
        // 	否	区块转账哈希记录
        private String txId;
        // 	否	提币状态-3：撤销中 -2：已撤销 -1：失败 0：等待提现 1：提现中 2：已汇出 3：邮箱确认 4：人工审核中 5：等待身份认证
        private String state;
        // 	否	查询在此之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085
        private String after;
        // 	否	查询在此之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085
        private String before;
        // 	否	返回的结果集数量，默认为100，最大为100，不填默认返回100条
        private String limit;
    }
}
