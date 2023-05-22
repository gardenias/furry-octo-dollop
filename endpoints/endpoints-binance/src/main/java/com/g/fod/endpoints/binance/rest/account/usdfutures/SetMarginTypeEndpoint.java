package com.g.fod.endpoints.binance.rest.account.usdfutures;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.account.usdfutures.SetMarginTypeEndpoint.SetMarginTypeReq;
import com.g.fod.endpoints.binance.rest.account.usdfutures.SetMarginTypeEndpoint.SetMarginTypeResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 变换逐全仓模式 (TRADE)
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#trade-10"
 */
public class SetMarginTypeEndpoint extends AbstractBinanceRESTEndpoint<SetMarginTypeReq, SetMarginTypeResp> {

  public SetMarginTypeEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/fapi/v1/marginType", HttpMethod.POST);
  }

  @Data
  public static class SetMarginTypeReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;
    private String marginType;//保证金模式 ISOLATED(逐仓), CROSSED(全仓)
    private String symbol;

  }

  @Data
  public static class SetMarginTypeResp extends Resp {

  }

}
