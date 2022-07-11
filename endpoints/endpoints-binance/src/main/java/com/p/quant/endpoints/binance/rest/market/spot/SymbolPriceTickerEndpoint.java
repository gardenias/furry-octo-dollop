package com.p.quant.endpoints.binance.rest.market.spot;

import java.util.List;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import com.p.quant.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerReq;
import com.p.quant.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 最新价格
 * @author：wanghao
 * @see ""
 * @date  ：Created in 2022/7/7
 */
public class SymbolPriceTickerEndpoint extends AbstractBinanceRESTEndpoint<SymbolPriceTickerReq, SymbolPriceTickerResp> {

    public SymbolPriceTickerEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/api/v3/ticker/price", HttpMethod.GET);
    }
    @Data
    public static class SymbolPriceTickerReq extends BinanceReq {
        private String symbol;
        private Integer limit;


    }
    @Data
    public static class SymbolPriceTickerResp extends Resp {
        private int id;
        private String price;
        private String qty;
        private String quoteQty;
        private long time;
        private boolean isBuyerMaker;
    }

   
}
