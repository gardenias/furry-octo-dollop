package com.g.fod.endpoints.binance.rest.market.spot;

import java.util.List;

import com.g.fod.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.g.fod.endpoints.binance.base.BinanceReq;
import com.g.fod.endpoints.binance.common.SignatureProducer;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesReq;
import com.g.fod.endpoints.binance.rest.market.spot.KlinesEndpoint.KlinesResp;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * K线数据
 *
 * @author：wanghao
 * @date ：Created in 2022/7/7
 * @see ""
 */
public class KlinesEndpoint extends AbstractBinanceRESTEndpoint<KlinesReq, KlinesResp> {

  public KlinesEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
    super(webClient, signatureProducer, "/api/v3/klines", HttpMethod.GET);
  }

  @Data
  public static class KlinesReq extends BinanceReq {

    private String symbol;//	STRING	YES
    private String interval;//	ENUM	YES	详见枚举定义：K线间隔
    private Long startTime;//	LONG	NO
    private Long endTime;//	LONG	NO
    private Integer limit;//	INT	NO	默认 500; 最大 1000.
  }

  @Data
  public static class KlinesResp extends Resp {

    List<List<Object>> result;
  }

}
