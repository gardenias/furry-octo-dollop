package com.g.fod.apps.rt.autoconfiguration;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.g.fod.apps.rt.CommandHandler;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.g.fod.apps.rt.CommandWrapperEvent;
import com.g.fod.apps.rt.DisruptorDsl;
import com.g.fod.endpoints.mexc.MexcApiSpec;
import com.g.fod.endpoints.mexc.MexcSignatureHeadersProducer;
import com.g.fod.endpoints.mexc.MexcWSClient;
import com.g.fod.endpoints.mexc.ws.WsResp;
import com.p.common.base.thread.ThreadFactories;
import com.p.common.base.web.WebClientFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BeanAutoConfiguration {

  private static final String baseUrl = "https://contract.mexc.com";
  private static final String key = "mx06rtWAgbVQuDs9aP";
  private static final String sec = "e749ba941f434d369e2ab1caebf1a19c";

  @Bean
  MexcApiSpec spec() {
    val headersProducer = new MexcSignatureHeadersProducer(key, sec);
    return new MexcApiSpec(WebClientFactory.create(baseUrl, httpHeaders ->
      httpHeaders.add("Content-Type", "application/json")), headersProducer);
  }

  @Bean(destroyMethod = "close")
  MexcWSClient wsClient(List<Consumer<WsResp>> consumers) {
    val wsClient = new MexcWSClient(
      wsResp -> consumers.forEach(consumer -> consumer.accept(wsResp)),
      LoggerFactory.getLogger(MexcWSClient.class), "wss://contract.mexc.com/ws",
      TimeUnit.SECONDS.toMillis(200), "");
    wsClient.start().doOnSuccess(latency -> log.info("Established latency: {} ms", latency)).block();
    wsClient.login(key, sec);
    return wsClient;
  }

  @Bean
  Disruptor<CommandWrapperEvent> disruptor() {
    return new Disruptor<>(CommandWrapperEvent::new, 1024,
      ThreadFactories.withName("round-trip-%d"), ProducerType.MULTI,
      new SleepingWaitStrategy(10, TimeUnit.MILLISECONDS.toNanos(20)));
  }

  @Bean(initMethod = "start", destroyMethod = "close")
  DisruptorDsl context(Disruptor<CommandWrapperEvent> disruptor, CommandHandler commandHandler) {
    val dsl = new DisruptorDsl(disruptor);
    dsl.handleEventsWith(commandHandler);
    dsl.setDefaultExceptionHandler(new IgnoreExceptionHandler());
    return dsl;
  }
}
