package com.p.quant.endpoints.binance.rest.trade.usdfutures;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.enums.RequestType;

import com.p.quant.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderReq;

import com.p.quant.endpoints.binance.rest.trade.usdfutures.CancelUseFuturesOrderEndpoint.CancelUseFuturesOrderResp;

import lombok.Data;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 撤销订单
 * @author：wanghao
 * @see ""
 * @date  ：Created in 2022/7/7
 */
public class CancelUseFuturesOrderEndpoint extends AbstractBinanceRESTEndpoint<CancelUseFuturesOrderReq, CancelUseFuturesOrderResp> {

    public CancelUseFuturesOrderEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v1/order", HttpMethod.DELETE, RequestType.SIGNED);
    }
    @Data
    public static class CancelUseFuturesOrderReq extends BinanceReq {
        private Long recvWindow;
        private Long timestamp;
        private String symbol;//STRING	YES
        private Long orderId;//LONG	NO
        private String  origClientOrderId;//	STRING	NO
    }
    @Data
    public static class CancelUseFuturesOrderResp extends Resp {

        private String clientOrderId;
        private String cumQty;
        private String cumQuote;
        private String executedQty;
        private long orderId;
        private String origQty;
        private String price;
        private boolean reduceOnly;
        private String side;
        private String positionSide;
        private String status;
        private String stopPrice;
        private boolean closePosition;
        private String symbol;
        private String timeInForce;
        private String origType;
        private String type;
        private String activatePrice;
        private String priceRate;
        private long updateTime;
        private String workingType;
        private boolean priceProtect;
    }

   
}
