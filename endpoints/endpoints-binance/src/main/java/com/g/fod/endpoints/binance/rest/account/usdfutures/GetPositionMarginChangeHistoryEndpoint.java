package com.g.fod.endpoints.binance.rest.account.usdfutures;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.account.usdfutures.GetPositionMarginChangeHistoryEndpoint.GetPositionMarginChangeHistoryReq;
import com.g.fod.endpoints.binance.rest.account.usdfutures.GetPositionMarginChangeHistoryEndpoint.GetPositionMarginChangeHistoryResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 逐仓保证金变动历史
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#trade-13"
 */
public class GetPositionMarginChangeHistoryEndpoint extends
  AbstractBinanceRESTEndpoint<GetPositionMarginChangeHistoryReq, GetPositionMarginChangeHistoryResp> {

  public GetPositionMarginChangeHistoryEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/positionMargin/history", HttpMethod.GET);
  }

  @Data
  public static class GetPositionMarginChangeHistoryReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;
    private Long startTime;
    private Long endTime;
    private Integer limit;
    private String symbol;//交易对
    private Integer type;//调整方向 1: 增加逐仓保证金，2: 减少逐仓保证金

  }

  @Data
  public static class GetPositionMarginChangeHistoryResp extends Resp {

    private String amount;
    private String asset;
    private String symbol;
    private long time;
    private int type;
    private String positionSide;
  }

}
