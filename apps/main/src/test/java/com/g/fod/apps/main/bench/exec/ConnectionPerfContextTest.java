package com.g.fod.apps.main.bench.exec;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.g.fod.endpoints.x.ws.XWSClient;

import lombok.val;
import com.google.common.util.concurrent.RateLimiter;

import com.g.common.endpoints.core.ws.AbstractWebSocketClient;
import com.timgroup.statsd.NoOpStatsDClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.handler.TextWebSocketHandler;

class ConnectionPerfContextTest {

  @Test
  void establishLatencyTests() throws Exception {
    val clientSupplier = (Supplier<AbstractWebSocketClient>) () ->
      new XWSClient(new TextWebSocketHandler(),
        "ws://localhost:8080", 100, "");
    val context = new ConnectionPerfContext(RateLimiter.create(100), 10,
      new NoOpStatsDClient(), clientSupplier);

    long stop = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1);
    while (System.currentTimeMillis() < stop) {
      TimeUnit.SECONDS.sleep(10);
    }
    context.close();
  }
}
