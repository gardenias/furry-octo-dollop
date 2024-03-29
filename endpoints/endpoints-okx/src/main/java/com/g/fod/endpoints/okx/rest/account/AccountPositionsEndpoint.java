package com.g.fod.endpoints.okx.rest.account;

import java.util.List;

import com.g.fod.endpoints.okx.domain.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.g.fod.openapi.spec.domain.Dict.InstType;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.g.fod.endpoints.okx.domain.InstId;
import com.g.fod.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsReq;
import com.g.fod.endpoints.okx.rest.account.AccountPositionsEndpoint.AccountPositionsResp;

public class AccountPositionsEndpoint extends AbstractRESTEndpoint<AccountPositionsReq, AccountPositionsResp> {

  public AccountPositionsEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/account/positions", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class AccountPositionsResp extends Resp {

    private List<Position> data;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class AccountPositionsReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 	否	产品类型MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权 instType和instId同时传入的时候会校验instId与instType是否一致。
    private InstType instType;
    // 	否	交易产品ID，如：BTC-USD-190927-5000-C 支持多个instId查询（不超过10个），半角逗号分隔
    private InstId instId;
    // 	否	持仓ID支持多个posId查询（不超过20个），半角逗号分割
    private String posId;
  }
}
