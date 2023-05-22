package com.g.fod.endpoints.binance.rest.market.spot;

import java.util.List;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookReq;
import com.g.fod.endpoints.binance.rest.market.spot.OrderBookEndpoint.OrderBookResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 深度信息
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/spot/cn/#38a975b802"
 */
public class OrderBookEndpoint extends AbstractBinanceRESTEndpoint<OrderBookReq, OrderBookResp> {

  public OrderBookEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/depth", HttpMethod.GET);
  }

  @Data
  public static class OrderBookReq extends BinanceReq {

    private Integer limit;
    private String symbol;

  }

  @Data
  public static class OrderBookResp extends Resp {

    private long lastUpdateId;
    private List<List<String>> bids;
    private List<List<String>> asks;
  }

}
