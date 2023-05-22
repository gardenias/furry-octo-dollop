package com.g.fod.apps.main.bench.handler;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitReq;

import lombok.extern.slf4j.Slf4j;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;

import com.g.common.endpoints.core.rest.SymbolReq;
import com.g.fod.apps.main.bench.core.MarketEvent;
import com.g.fod.apps.main.bench.core.ReqExecWorkHandler;
import com.timgroup.statsd.NoOpStatsDClient;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
class ReqExecWorkHandlerTest {

  @Test
  void publishOnToSwitchTreadTest() throws Exception {
    Executor ec = Executors.newScheduledThreadPool(10000);
    ExecutorService executor = Executors.newFixedThreadPool(10);
    ReqExecWorkHandler handler = new ReqExecWorkHandler(
      req -> Mono.delay(Duration.ofMillis(100),
        Schedulers.fromExecutor(ec)).map(l -> {
        log.info("xx{}", l);
        return l;
      }), new NoOpStatsDClient(), RateLimiter.create(1000));

    MarketEvent event = new MarketEvent();
    List<SymbolReq> reqs = Lists.newArrayList(new SubmitReq("1"), new SubmitReq("2"),
      new SubmitReq("3"));

    event.setReqs(reqs);

    handler.onEvent(event.getReqs());

    System.out.println("122");

    TimeUnit.SECONDS.sleep(1);
  }
}
