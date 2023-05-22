package com.g.fod.endpoints.binance.rest.account.margin;

import java.util.List;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.account.margin.CrossMarginAccountDetailsEndpoint.CrossMarginAccountDetailsReq;
import com.g.fod.endpoints.binance.rest.account.margin.CrossMarginAccountDetailsEndpoint.CrossMarginAccountDetailsResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 查询全仓杠杆账户详情
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see "https://binance-docs.github.io/apidocs/spot/cn/#user_data-27"
 */
public class CrossMarginAccountDetailsEndpoint extends
  AbstractBinanceRESTEndpoint<CrossMarginAccountDetailsReq, CrossMarginAccountDetailsResp> {

  public CrossMarginAccountDetailsEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/sapi/v1/margin/account", HttpMethod.GET);
  }

  @Data
  public static class CrossMarginAccountDetailsResp extends Resp {

    private boolean borrowEnabled;
    private String marginLevel;
    private String totalAssetOfBtc;
    private String totalLiabilityOfBtc;
    private String totalNetAssetOfBtc;
    private boolean tradeEnabled;
    private boolean transferEnabled;
    private List<UserAssets> userAssets;
  }

  @Data
  class UserAssets {

    private String asset;
    private String borrowed;
    private String free;
    private String interest;
    private String locked;
    private String netAsset;
  }

  @Data
  public static class CrossMarginAccountDetailsReq extends BinanceReq {

    private Long recvWindow;
    private Long timestamp;
  }
}
