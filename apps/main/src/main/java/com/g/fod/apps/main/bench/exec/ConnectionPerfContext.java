package com.g.fod.apps.main.bench.exec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;
import com.google.common.util.concurrent.RateLimiter;

import com.g.common.endpoints.core.ws.AbstractWebSocketClient;
import com.p.common.base.thread.ThreadFactories;
import com.timgroup.statsd.StatsDClient;

@Slf4j
public class ConnectionPerfContext implements AutoCloseable {

  private final ExecutorService executorService;

  public ConnectionPerfContext(RateLimiter rateLimiter, int nThreads, StatsDClient dClient,
    Supplier<AbstractWebSocketClient> socketClientSupplier) {
    this.executorService =
      Executors.newFixedThreadPool(nThreads + 1, ThreadFactories.withName("cpc-%d"));
    int count = 0;
    while (count++ < nThreads) {
      executorService.submit(() -> {
        AbstractWebSocketClient client = socketClientSupplier.get();
        while (!Thread.currentThread().isInterrupted()) {
          try {
            rateLimiter.acquire();
            client.start().doOnSuccess(latency -> {
              dClient.time("ws.client.established", latency);
              try {
                client.close();
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            }).block();
          } catch (Exception e) {
            log.error(e.getMessage());
          }
        }
      });
    }
  }

  @Override
  public void close() {
    executorService.shutdown();
  }
}
