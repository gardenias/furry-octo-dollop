package com.g.fod.endpoints.okx.ws;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.g.fod.openapi.spec.domain.Dict;
import com.g.common.endpoints.core.ws.DisruptorWebSocketHandler;
import com.g.common.endpoints.core.ws.MessageWrapper;
import com.lmax.disruptor.dsl.Disruptor;
import com.p.common.base.thread.ThreadFactories;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.g.fod.endpoints.okx.domain.InstId;

@Slf4j
class OkxV5WSUsrSpecTest {

  private static final InstId INST_ID = new InstId("BTC-USDT-SWAP");
  private OkxV5WSUsrSpecImpl client;
  private Disruptor<MessageWrapper<String>> disruptor;

  @BeforeEach
  void setUp() throws InterruptedException {
    disruptor = new Disruptor<>(MessageWrapper::new, 1024, ThreadFactories.withName("tt-%d"));
    disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
      val payload = event.getMessage().getPayload();
      log.info("[received]: {}", payload);
    });

    disruptor.start();

    client = new OkxV5WSUsrSpecImpl(new DisruptorWebSocketHandler(disruptor));
    client.start().doOnSuccess(latency -> System.out.println("Established latency:" + latency + "ms")).block();

    login();
    TimeUnit.SECONDS.sleep(1);
  }

  @AfterEach
  void tearDown() throws InterruptedException {
    TimeUnit.SECONDS.sleep(10);
    client.close();
    disruptor.shutdown();
  }

  void login() {
    String key = "7dc6fab8-e3d0-4a95-a740-17e74f0282c4";
    String passphrase = "OKXLr001!";
    String sec = "5514A17E24308A0CD8D866D35DC2C389";

    client.login(key, sec, passphrase);
  }

  @Test
  void account() {
    client.account();
  }

  @Test
  void testAccount() {
    client.account("USDT");
  }

  @Test
  void positions() {
    client.positions(Dict.InstType.SWAP);
    client.positions(Dict.InstType.SWAP, INST_ID);
  }

  @Test
  void orders() {
    client.orders(Dict.InstType.SWAP);
    client.orders(Dict.InstType.SWAP, INST_ID);
  }

  @Test
  void ordersAlgo() {
    client.ordersAlgo(Dict.InstType.SWAP);
    client.ordersAlgo(Dict.InstType.SWAP, INST_ID);
  }

  @Test
  void algoAdvance() {
    client.algoAdvance(Dict.InstType.SWAP);
    client.algoAdvance(Dict.InstType.SWAP, INST_ID);
  }

  @Test
  void liquidationWarning() {
    client.liquidationWarning(Dict.InstType.ANY);
  }

  @Test
  void balanceAndPosition() {
    client.balanceAndPosition();
  }

  @Test
  void submit() {
  }

  @Test
  void cancel() {
  }

  @Test
  void amend() {
  }
}
