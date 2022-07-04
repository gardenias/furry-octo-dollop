package com.ladder.quant.endpoints.okx.rest.asset;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalReq;
import com.ladder.quant.endpoints.okx.rest.asset.WithdrawalEndpoint.WithdrawalResp;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.p.common.base.json.JsonPrinter;

public class WithdrawalEndpoint extends AbstractRESTEndpoint<WithdrawalReq, WithdrawalResp> {

    public WithdrawalEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/asset/withdrawal", HttpMethod.POST);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class WithdrawalResp extends Resp {

        private List<Data> data;
    }

    public static class Data implements Serializable {

        // 	提币币种
        private String ccy;
        // 	币种链信息 有的币种下有多个链，必须要做区分，如USDT下有USDT-ERC20，USDT-TRC20，USDT-Omni多个链
        private String chain;
        // 	提币数量
        private String amt;
        // 	提币申请ID
        private String wdId;
        // 	客户自定义ID
        private String clientId;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class WithdrawalReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 	是	币种，如 USDT
        private String ccy;
        // 	是	数量
        private String amt;
        // 	是	提币到 3：OKX 4：数字货币地址
        private String dest;
        // 	是	认证过的数字货币地址、邮箱或手机号。某些数字货币地址格式为:地址+标签，如 ARDOR-7JF3-8F2E-QUWZ-CAN7F:123456
        private String toAddr;
        // 	是	网络手续费≥0，提币到数字货币地址所需网络手续费可通过获取币种列表接口查询
        private String fee;
        // 	可选	币种链信息如USDT下有USDT-ERC20，USDT-TRC20，USDT-Omni多个链 如果没有不填此参数，则默认为主链
        private String chain;
        // 	否	客户自定义ID字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
        private String clientId;
    }
}
