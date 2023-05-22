package com.g.fod.endpoints.binance.rest.market.spot;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerReq;
import com.g.fod.endpoints.binance.rest.market.spot.SymbolPriceTickerEndpoint.SymbolPriceTickerResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 最新价格
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class SymbolPriceTickerEndpoint extends
  AbstractBinanceRESTEndpoint<SymbolPriceTickerReq, SymbolPriceTickerResp> {

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
