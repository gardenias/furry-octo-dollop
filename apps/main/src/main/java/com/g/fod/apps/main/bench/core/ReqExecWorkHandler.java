package com.g.fod.apps.main.bench.core;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitReq;

import lombok.extern.slf4j.Slf4j;
import com.google.common.util.concurrent.RateLimiter;

import com.g.common.endpoints.core.rest.SymbolReq;
import com.timgroup.statsd.StatsDClient;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ReqExecWorkHandler {

  private final Function<SymbolReq, Mono<Long>> reqExecLatency;
  private final StatsDClient dClient;
  private final RateLimiter rateLimiter;

  public ReqExecWorkHandler(Function<SymbolReq, Mono<Long>> reqExecLatency,
    StatsDClient dClient, RateLimiter rateLimiter) {
    this.reqExecLatency = reqExecLatency;
    this.dClient = dClient;
    this.rateLimiter = rateLimiter;
  }

  public void onEvent(List<SymbolReq> reqs) throws Exception {
    if (reqs == null || reqs.isEmpty()) {
      return;
    }
    try {
      Flux.fromIterable(reqs)
        .filter(req1 -> rateLimiter.tryAcquire(Duration.ofNanos(100)))
        .flatMap((Function<SymbolReq, Publisher<SymbolReq>>) req -> {
          if (req instanceof SubmitReq) {
            ((SubmitReq) req).setExternalOid(
              System.nanoTime() + "nanomill" + System.currentTimeMillis());
          }
          return Mono.just(req);
        }).flatMap(reqExecLatency)
        .subscribe(latency -> {
          if (log.isTraceEnabled()) {
            log.trace("round.trip.to_ack={},type={}", latency,
              SubmitReq.class.getSimpleName());
          }
          dClient.time("round.trip.to_ack", latency,
            "type:" + SubmitReq.class.getSimpleName());
        });
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }
}
