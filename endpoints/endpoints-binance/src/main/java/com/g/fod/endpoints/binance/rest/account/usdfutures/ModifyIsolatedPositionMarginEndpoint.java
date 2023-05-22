package com.g.fod.endpoints.binance.rest.account.usdfutures;

import java.math.BigDecimal;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.account.usdfutures.ModifyIsolatedPositionMarginEndpoint.ModifyIsolatedPositionMarginReq;
import com.g.fod.endpoints.binance.rest.account.usdfutures.ModifyIsolatedPositionMarginEndpoint.ModifyIsolatedPositionMarginResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 调整逐仓保证金
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class ModifyIsolatedPositionMarginEndpoint extends
  AbstractBinanceRESTEndpoint<ModifyIsolatedPositionMarginReq, ModifyIsolatedPositionMarginResp> {

  public ModifyIsolatedPositionMarginEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/positionMargin", HttpMethod.GET);
  }

  @Data
  public static class ModifyIsolatedPositionMarginReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;

    private String symbol;//交易对
    private String positionSide;//持仓方向，单向持仓模式下非必填，默认且仅可填BOTH;在双向持仓模式下必填,且仅可选择 LONG 或 SHORT
    private BigDecimal amount;//保证金资金
    private Integer type;//调整方向 1: 增加逐仓保证金，2: 减少逐仓保证金

  }

  @Data
  public static class ModifyIsolatedPositionMarginResp extends Resp {

    private BigDecimal amount;
    private Integer type;
  }

}
