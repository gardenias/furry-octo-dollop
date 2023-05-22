package com.g.fod.apps.main.bench.context;

import com.google.common.util.concurrent.RateLimiter;

import com.lmax.disruptor.FatalExceptionHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.g.fod.apps.main.bench.core.MarketEvent;
import com.p.common.base.DisruptorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorAutoConfiguration {

  @Bean
  RateLimiter rateLimiter(
    @Value("${rate.limiter.permits-per-second:100}") double permitsPerSecond) {
    return RateLimiter.create(permitsPerSecond);
  }

  @Bean
  public Disruptor<MarketEvent> marketEventDisruptor() {
    Disruptor<MarketEvent> disruptor = DisruptorFactory.single(MarketEvent::new, 1024 * 4,
      "HBEvents");
    disruptor.setDefaultExceptionHandler(new FatalExceptionHandler());
    return disruptor;
  }
}
