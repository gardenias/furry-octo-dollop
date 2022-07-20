package com.ladder.quant.endpoints.okx.rest.asset;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.ladder.quant.endpoints.core.rest.AbstractRESTEndpoint;
import com.ladder.quant.endpoints.core.rest.HeadersProducer;
import com.ladder.quant.endpoints.core.rest.Req;
import com.ladder.quant.endpoints.core.rest.Resp;
import com.ladder.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferReq;
import com.ladder.quant.endpoints.okx.rest.asset.TransferEndpoint.TransferResp;

public class TransferEndpoint extends AbstractRESTEndpoint<TransferReq, TransferResp> {

  public TransferEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/asset/transfer", HttpMethod.POST);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class TransferResp extends Resp {

    private List<Data> data;
  }

  @Setter
  @Getter
  public static class Data implements Serializable {

    // 	划转 ID
    private String transId;
    // 	划转币种
    private String ccy;
    // 	转出账户
    private String from;
    // 	划转量
    private String amt;
    // 	转入账户
    private String to;
    // 	客户自定义ID
    private String clientId;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class TransferReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 	是	币种，如 USDT
    private String ccy;
    // 	是	划转数量
    private String amt;
    // 		是	转出账户 6：资金账户 18：交易账户
    private String from;
    // 		是	转入账户 6：资金账户 18：交易账户
    private String to;
    // 	可选	子账户名称，type 为1或2：subAcct 为必填项
    private String subAcct;
    /**
     * 划转类型 否 0：账户内划转 1：母账户转子账户(仅适用于母账户APIKey) 2：子账户转母账户(仅适用于母账户APIKey) 3：子账户转母账户(仅适用于子账户APIKey)
     * 4：子账户转子账户(仅适用于子账户APIKey，且目标账户需要是同一母账户下的其他子账户)
     */
    private String type;
    // 	否	是否支持跨币种保证金模式或组合保证金模式下的借币转入/转出 true 或 false，默认false
    private Boolean loanTrans;
    // 		否	客户自定义ID字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
    private String clientId;
  }
}
