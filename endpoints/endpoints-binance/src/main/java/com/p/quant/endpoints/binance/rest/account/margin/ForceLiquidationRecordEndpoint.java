package com.p.quant.endpoints.binance.rest.account.margin;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.account.margin.ForceLiquidationRecordEndpoint.ForceLiquidationRecordReq;
import com.p.quant.endpoints.binance.rest.account.margin.ForceLiquidationRecordEndpoint.ForceLiquidationRecordResp;


import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


/**
 * 获取账户强制平仓记录(USER_DATA)
 * @author：wanghao
 * @date  ：Created in 2022/7/7
 */
public class ForceLiquidationRecordEndpoint extends AbstractBinanceRESTEndpoint<ForceLiquidationRecordReq, ForceLiquidationRecordResp> {

    public ForceLiquidationRecordEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/sapi/v1/margin/forceLiquidationRec", HttpMethod.GET);
    }

    @Data
    public static class ForceLiquidationRecordResp extends Resp {
        private List<Rows> rows;
        private int total;
    }
    @Data
    public class Rows {

        private String avgPrice;
        private String executedQty;
        private long orderId;
        private String price;
        private String qty;
        private String side;
        private String symbol;
        private String timeInForce;
        private boolean isIsolated;
        private long updatedTime;
        }

    @Data
    public static class ForceLiquidationRecordReq extends BinanceReq {

        private Long startTime;
        private Long endTime;
        private String isolatedSymbol;
        private Long current;
        private Long size;
        private Long recvWindow;
        private Long timestamp;

    }
}
