package com.g.fod.endpoints.binance.rest.market.spot;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.g.fod.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 查询历史成交
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 */
public class RecentTradesListEndpoint extends AbstractBinanceRESTEndpoint<RecentTradesListReq, RecentTradesListResp> {

  public RecentTradesListEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, "/api/v3/trades", HttpMethod.GET);
  }

  @Data
  public static class RecentTradesListResp extends Resp {

    private Integer id;
    private String price;
    private String qty;
    private Long time;
    private boolean isBuyerMaker;
    private boolean isBestMatch;
  }

  @Data
  public static class RecentTradesListReq extends BinanceReq {

    private String symbol;// 必填
    private Integer limit;//

  }
}
