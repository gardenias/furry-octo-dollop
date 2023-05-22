package com.g.fod.endpoints.binance.rest.market.usdfutures;

import java.util.List;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesReq;
import com.g.fod.endpoints.binance.rest.market.usdfutures.OrderBookUsdFuturesEndpoint.OrderBookUsdFuturesResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 深度信息
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class OrderBookUsdFuturesEndpoint extends
  AbstractBinanceRESTEndpoint<OrderBookUsdFuturesReq, OrderBookUsdFuturesResp> {

  public OrderBookUsdFuturesEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/depth", HttpMethod.GET);
  }

  @Data
  public static class OrderBookUsdFuturesReq extends BinanceReq {

    private String symbol;
    private Integer limit;

  }

  @Data
  public static class OrderBookUsdFuturesResp extends Resp {

    private long lastUpdateId;
    private long E;
    private long T;
    private List<List<String>> bids;
    private List<List<String>> asks;
  }

}
