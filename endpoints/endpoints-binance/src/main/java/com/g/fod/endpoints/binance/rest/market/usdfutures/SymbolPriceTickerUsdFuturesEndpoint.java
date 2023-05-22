package com.g.fod.endpoints.binance.rest.market.usdfutures;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.SymbolPriceTickerUsdFuturesEndpoint.SymbolPriceTickerUsdFuturesResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 最新价格
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class SymbolPriceTickerUsdFuturesEndpoint extends
  AbstractBinanceRESTEndpoint<SymbolPriceTickerUsdFuturesReq, SymbolPriceTickerUsdFuturesResp> {

  public SymbolPriceTickerUsdFuturesEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/ticker/price", HttpMethod.GET);
  }

  @Data
  public static class SymbolPriceTickerUsdFuturesReq extends BinanceReq {

    private String symbol;
    private Integer limit;

  }

  @Data
  public static class SymbolPriceTickerUsdFuturesResp extends Resp {

    private int id;
    private String price;
    private String qty;
    private String quoteQty;
    private long time;
    private boolean isBuyerMaker;
  }

}
