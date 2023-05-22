package com.g.fod.endpoints.binance.rest.asset.margin;

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
 * 杠杆逐仓账户划转
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class IsolatedMarginAccountTransferEndpoint extends
  AbstractBinanceRESTEndpoint<AccountInfoReq, AccountInfoResp> {

  public IsolatedMarginAccountTransferEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/sapi/v1/margin/isolated/transfer", HttpMethod.POST);
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
