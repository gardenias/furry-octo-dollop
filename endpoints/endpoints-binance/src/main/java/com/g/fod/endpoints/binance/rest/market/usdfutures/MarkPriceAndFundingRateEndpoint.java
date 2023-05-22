package com.g.fod.endpoints.binance.rest.market.usdfutures;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;
import com.g.fod.endpoints.binance.rest.account.spot.AccountInfoEndpoint;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 最新标记价格和资金费率
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class MarkPriceAndFundingRateEndpoint extends AbstractBinanceRESTEndpoint<AccountInfoEndpoint.AccountInfoReq, AccountInfoEndpoint.AccountInfoResp> {

  public MarkPriceAndFundingRateEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/premiumIndex", HttpMethod.GET);
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
