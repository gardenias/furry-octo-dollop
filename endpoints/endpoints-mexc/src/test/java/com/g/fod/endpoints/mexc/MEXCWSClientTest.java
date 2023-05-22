package com.g.fod.endpoints.mexc;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class MEXCWSClientTest {

  MexcWSClient client;

  @BeforeEach
  void setUp() {
    val uriTemplate = "wss://contract.mexc.com/ws";
    client = new MexcWSClient(uriTemplate, TimeUnit.SECONDS.toMillis(200), "");
    client.start().doOnSuccess(latency -> System.out.println("Established latency:" + latency + "ms")).block();
  }

  @Test
  void ticker() {
    client.ticker("BTC_USDT");
  }

  @Test
  void login() {
    val key = "mx038Uufrlv4I7KHxw";
    val sec = "4bbdb8639e244f87980ad6091d670536";
    client.login(key, sec);
  }

  @AfterEach
  void tearDown() throws InterruptedException {
    TimeUnit.SECONDS.sleep(3);
    client.close();
  }
}
