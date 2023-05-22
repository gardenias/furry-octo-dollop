package com.g.fod.endpoints.okx.rest.market;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.g.common.endpoints.core.rest.AbstractRESTEndpoint;
import com.g.common.endpoints.core.rest.HeadersProducer;
import com.g.common.endpoints.core.rest.Req;
import com.g.common.endpoints.core.rest.Resp;
import com.p.common.base.json.JsonPrinter;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.g.fod.endpoints.okx.rest.market.BooksEndpoint.BooksReq;
import com.g.fod.endpoints.okx.rest.market.BooksEndpoint.BooksResp;

public class BooksEndpoint extends AbstractRESTEndpoint<BooksReq, BooksResp> {

  public BooksEndpoint(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer, "/api/v5/market/books", HttpMethod.GET);
  }

  @Setter
  @Getter
  @Accessors(fluent = true, chain = true)
  @ToString(callSuper = true)
  public static class BooksResp extends Resp {

    private List<Data> data;
  }

  @Setter
  @Getter
  public static class Data implements Serializable {

    private List<Item> asks;
    private List<Item> bids;
    private long ts;
  }

  @Setter
  @Getter
  @JsonFormat(shape = Shape.ARRAY)
  public static class Item implements Serializable {

    private String px;
    private int qty;
    private int ordQty;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @ToString(callSuper = true)
  public static class BooksReq extends Req {

    @Override
    public String getSignatureContent() {
      return JsonPrinter.jsonPrint(this);
    }

    // 	是	产品ID，如 BTC-USDT
    private String instId;
    // 	否	深度档位数量，最大值可传400，即买卖深度共800条 不填写此参数，默认返回1档深度数据
    private String sz;
  }
}
