package com.g.fod.endpoints.binance.rest.market.spot;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.enums.RequestType;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.g.fod.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 查询历史成交
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 */
public class OldTradeLookupEndpoint extends AbstractBinanceRESTEndpoint<OldTradeLookupReq, OldTradeLookupResp> {

  public OldTradeLookupEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/historicalTrades", HttpMethod.GET, RequestType.WITH_API_KEY);
  }

  @Data
  public static class OldTradeLookupResp extends Resp {

    private int id;
    private String price;
    private String qty;
    private String quoteQty;
    private long time;
    private boolean isBuyerMaker;
    private boolean isBestMatch;
  }

  @Data
  public static class OldTradeLookupReq extends BinanceReq {

    private String symbol;// 必填
    private Integer limit;//
    private Long fromId;

  }
}
