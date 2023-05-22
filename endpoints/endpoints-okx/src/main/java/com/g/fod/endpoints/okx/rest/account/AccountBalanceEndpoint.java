package com.g.fod.endpoints.okx.rest.account;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.g.fod.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceReq;
import com.g.fod.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceResp;

public class AccountBalanceEndpoint extends AbstractRESTEndpoint<AccountBalanceReq, AccountBalanceResp> {

  public AccountBalanceEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/account/balance", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class AccountBalanceResp extends Resp {

    // 	账户信息的更新时间，Unix时间戳的毫秒数格式，如 1597026383085
    private String uTime;
    // 	美金层面权益
    private String totalEq;
    // 	美金层面逐仓仓位权益 适用于单币种保证金模式和跨币种保证金模式和组合保证金模式
    private String isoEq;
    // 	美金层面有效保证金 适用于跨币种保证金模式和组合保证金模式
    private String adjEq;
    // 	美金层面全仓挂单占用保证金 适用于跨币种保证金模式和组合保证金模式
    private String ordFroz;
    // 	美金层面占用保证金 适用于跨币种保证金模式和组合保证金模式
    private String imr;
    // 	美金层面维持保证金 适用于跨币种保证金模式和组合保证金模式
    private String mmr;
    // 	美金层面保证金率 适用于跨币种保证金模式 和组合保证金模式
    private String mgnRatio;
    // 	以美金价值为单位的持仓数量，即仓位美金价值 适用于跨币种保证金模式和组合保证金模式
    private String notionalUsd;

    private List<Detail> details;
  }

  public static class Detail implements Serializable {

    // 	币种
    private String ccy;
    // 	币种总权益
    private String eq;
    // 	币种余额
    private String cashBal;
    // 	币种余额信息的更新时间，Unix时间戳的毫秒数格式，如 1597026383085
    private String uTime;
    // 	币种逐仓仓位权益 适用于单币种保证金模式和跨币种保证金模式和组合保证金模式
    private String isoEq;
    // 	可用保证金 适用于单币种保证金模式和跨币种保证金模式和组合保证金模式
    private String availEq;
    // 	美金层面币种折算权益
    private String disEq;
    // 	可用余额 适用于简单交易模式
    private String availBal;
    // 	币种占用金额
    private String frozenBal;
    // 	挂单冻结数量
    private String ordFrozen;
    // 	币种负债额 适用于跨币种保证金模式和组合保证金模式
    private String liab;
    // 	未实现盈亏 适用于单币种保证金模式和跨币种保证金模式和组合保证金模式
    private String upl;
    // 	由于仓位未实现亏损导致的负债 适用于跨币种保证金模式和组合保证金模式
    private String uplLiab;
    // 	币种全仓负债额 适用于跨币种保证金模式和组合保证金模式
    private String crossLiab;
    // 	币种逐仓负债额 适用于跨币种保证金模式和组合保证金模式
    private String isoLiab;
    // 	保证金率 适用于单币种保证金模式
    private String mgnRatio;
    // 	计息 适用于跨币种保证金模式和组合保证金模式
    private String interest;
    // 	当前负债币种触发系统自动换币的风险 0、1、2、3、4、5其中之一，数字越大代表您的负债币种触发自动换币概率越高 适用于跨币种保证金模式和组合保证金模式
    private String twap;
    // 	币种最大可借 适用于跨币种保证金模式和组合保证金模式
    private String maxLoan;
    // 	币种权益美金价值
    private String eqUsd;
    // 	币种杠杆倍数 适用于单币种保证金模式
    private String notionalLever;
    // 	策略权益
    private String stgyEq;
    // 	逐仓未实现盈亏 适用于单币种保证金模式和跨币种保证金模式和组合保证金模式
    private String isoUpl;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class AccountBalanceReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 否	币种，如 BTC支持多币种查询（不超过20个），币种之间半角逗号分隔
    private String ccy;
  }
}
