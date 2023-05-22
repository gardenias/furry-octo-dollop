package com.g.fod.endpoints.mexc;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import com.g.common.endpoints.core.rest.Resp;
import com.g.fod.endpoints.mexc.endpoints.AccountAssetsEndpoint.AssetsReq;
import com.g.fod.endpoints.mexc.endpoints.AccountAssetsEndpoint.AssetsResp;
import com.g.fod.endpoints.mexc.endpoints.CancelAllEndpoint.CancelAllResp;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelResp;
import com.g.fod.endpoints.mexc.endpoints.QueryActiveOrderEndpoint;
import com.g.fod.endpoints.mexc.endpoints.QueryOrderEndpoint.QueryResp;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.Price;
import com.p.common.base.json.JsonPrinter;
import com.p.common.base.web.WebClientFactory;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

class MexcApiSpecTest {

  private static final String baseUrl = "https://contract.mexc.com";

  private final String key = "mx06rtWAgbVQuDs9aP";
  private final String sec = "e749ba941f434d369e2ab1caebf1a19c";

  final MexcSignatureHeadersProducer headersProducer = new MexcSignatureHeadersProducer(key, sec);
  final MexcApiSpec operator = new MexcApiSpec(WebClientFactory.create(baseUrl, httpHeaders ->
    httpHeaders.add("Content-Type", "application/json")), headersProducer);

  @Test
  void place() {
    Mono<OrderId> ordIdMono = operator.openLong("BTC_USDT", Price.of(new BigDecimal("36000.00")), 1);
    OrderId ordId = ordIdMono.block();
    System.out.println(ordId);
    QueryResp order = getByOrdId(ordId);
    System.out.println(JsonPrinter.prettyPrint(order));
    CancelResp cancelResp = operator.cancel(ordId).block();
    System.out.println(cancelResp);
  }

  @Test
  void queryOrder() {
    QueryResp order = getByOrdId(OrderId.of(256167033543152640L));
    System.out.println(order);
  }

  @Test
  void queryOrderNotFound() {
    QueryResp order = getByOrdId(OrderId.of(25616733543152640L));
    System.out.println(order);
  }

  private QueryResp getByOrdId(OrderId ordId) {
    return operator.getByOrdId(ordId).block();
  }

  @Test
  void getAll() {
    Mono<QueryActiveOrderEndpoint.QueryResp> respMono = operator.getAll("BTC_USDT");
    System.out.println(JsonPrinter.prettyPrint(respMono.block().getData()));
  }

  @Test
  void cancel() {
    long ordId = 256535699178736128L;
    CancelResp cancelResp = operator.cancel(OrderId.of(ordId)).block();
    System.out.println(cancelResp);
  }

  @Test
  void cancelAll() {
    Mono<CancelAllResp> respMono = operator.cancelAll();
    System.out.println(respMono.block());
  }

  @Test
  void assets() {
    Mono<AssetsResp> assets = operator.assets(
      new AssetsReq());
    Resp response = assets.block(Duration.of(5, ChronoUnit.SECONDS));
    System.out.println(response);
  }

  @Test
  void ping() {
    long ts = operator.ping();
    assertThat(System.currentTimeMillis() - ts).isLessThan(TimeUnit.SECONDS.toMillis(1));
  }
}
