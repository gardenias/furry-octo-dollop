package com.g.fod.endpoints.mexc.endpoints;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.mexc.endpoints.AccountAssetsEndpoint.AssetsReq;
import com.g.fod.endpoints.mexc.endpoints.AccountAssetsEndpoint.AssetsResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class AccountAssetsEndpoint extends
  AbstractRESTEndpoint<AssetsReq, AssetsResp> {

  public AccountAssetsEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v1/private/account/assets", HttpMethod.GET);
  }

  @Getter
  @Setter
  public static class AssetsResp extends Resp {

    private List<Asset> data;

    @Override
    public String toString() {
      return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Asset implements Serializable {

      //	币种
      private String currency;
      //	仓位保证金
      private BigDecimal positionMargin;
      //	冻结余额
      private BigDecimal frozenBalance;
      //	当前可用余额
      private BigDecimal availableBalance;
      //	可提现余额
      private BigDecimal cashBalance;
      //	总权益
      private BigDecimal equity;
      //	未实现盈亏
      private BigDecimal unrealized;

      @Override
      public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
      }
    }
  }

  @Slf4j
  public static class AssetsReq extends Req {

    @Override
    public String getSignatureContent() {
      return "";
    }
  }
}
