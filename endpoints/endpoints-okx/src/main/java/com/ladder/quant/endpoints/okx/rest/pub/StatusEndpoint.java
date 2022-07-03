package com.ladder.quant.endpoints.okx.rest.pub;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.ladder.quant.endpoints.okx.rest.pub.StatusEndpoint.StatusReq;
import com.ladder.quant.endpoints.okx.rest.pub.StatusEndpoint.StatusResp;
import com.ladder.quant.endpoints.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.rest.HeadersProducer;
import com.ladder.quant.endpoints.rest.Req;
import com.ladder.quant.endpoints.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.common.base.json.JsonPrinter;

public class StatusEndpoint extends AbstractRESTEndpoint<StatusReq, StatusResp> {

    public StatusEndpoint(WebClient webClient, HeadersProducer headersProducer) {
        super(webClient, headersProducer, "/api/v5/system/status", HttpMethod.GET);
    }

    @Setter
    @Getter
    @Accessors(fluent = true, chain = true)
    @ToString(callSuper = true)
    public static class StatusResp extends Resp {

        private List<Data> data;
    }

    @Setter
    @Getter
    public static class Data implements Serializable {

        // 	系统维护说明的标题
        private String title;
        // 	系统维护的状态
        private String state;
        // 	系统维护的开始时间,Unix时间戳的毫秒数格式 如：1617788463867
        private String begin;
        // 	系统维护的结束时间,Unix时间戳的毫秒数格式 如：1617788463867在维护完成前，是预期结束时间；维护完成后，会变更为实际结束时间。
        private String end;
        // 	系统维护详情的超级链接,若无返回值，默认值为空，如： “”
        private String href;
        // 	服务类型， 0：WebSocket ; 5：交易服务
        private String serviceType;
        // 	系统，unified：交易账户
        private String system;
        // 	改期进度说明，如： 由 2021-01-26T16:30:00.000Z 改期到 2021-01-28T16:30:00.000Z
        private String scheDesc;

    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @ToString(callSuper = true)
    public static class StatusReq extends Req {

        @Override
        public String getSignatureContent() {
            return JsonPrinter.jsonPrint(this);
        }

        // 系统的状态，scheduled:等待中 ; ongoing:进行中 ; completed:已完成 canceled: 已取消 不填写此参数，默认返回 等待中和进行中 的数据
        private String state;

    }
}
