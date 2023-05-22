package com.g.fod.endpoints.binance.rest.asset.spot;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 获取提币历史 (支持多网络)
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class DepositHistoryEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public DepositHistoryEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/sapi/v1/capital/withdraw/history", HttpMethod.GET);
  }

  @Data
  public static class AccountInfoReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;

  }

  @Data
  public static class AccountInfoResp extends Resp {

  }

}
