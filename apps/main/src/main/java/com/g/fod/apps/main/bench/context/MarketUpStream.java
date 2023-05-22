package com.g.fod.apps.main.bench.context;

import java.util.concurrent.TimeUnit;

import com.g.fod.apps.main.bench.core.ReqProduceHandler;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import com.google.common.util.concurrent.RateLimiter;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.g.fod.apps.main.bench.core.MarketEvent;
import com.g.fod.apps.main.bench.core.MarketEventProducer;
import com.g.fod.apps.main.bench.core.ReqExecWorkHandler;
import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(value = "s", havingValue = "1")
public class MarketUpStream implements AutoCloseable {

  private final Disruptor<MarketEvent> disruptor;
  private final MarketEventProducer eventProducer;

  @Autowired
  public MarketUpStream(Disruptor<MarketEvent> disruptor, UserContext userContext,
    StatsDClient dClient, MarketEventProducer producer, RateLimiter rateLimiter)
    throws Exception {

    this.eventProducer = producer;

    val workHandlers = userContext.getOrderOperatorMap()
      .values().stream().map(apiSpec -> new EventHandler<MarketEvent>() {
        final ReqExecWorkHandler reqExecWorkHandler = null;
//                    new ReqExecWorkHandler(req -> req.exec(apiSpec), dClient, rateLimiter);

        @Override
        public void onEvent(MarketEvent event, long sequence, boolean endOfBatch) {
          try {
            reqExecWorkHandler.onEvent(event.getReqs());
          } catch (Exception e) {
            log.error(e.getMessage(), e);
          }
        }
      }).toArray(EventHandler[]::new);

    log.info("req exec work handler count : {}", workHandlers.length);
    this.disruptor = disruptor;
    this.disruptor.handleEventsWith(new ReqProduceHandler()).handleEventsWith(workHandlers);

    this.disruptor.start();
    this.eventProducer.start();
  }

  @Override
  public void close() throws Exception {
    eventProducer.close();
    disruptor.shutdown(5, TimeUnit.SECONDS);
  }
}
