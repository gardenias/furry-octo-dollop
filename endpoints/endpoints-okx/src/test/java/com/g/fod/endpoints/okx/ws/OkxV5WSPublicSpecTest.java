package com.g.fod.endpoints.okx.ws;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import com.g.fod.openapi.spec.domain.Dict.InstType;
import com.g.common.endpoints.core.ws.LoggingWebSocketHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.g.fod.endpoints.okx.domain.Dict;
import com.g.fod.endpoints.okx.domain.InstId;

@Slf4j
class OkxV5WSPublicSpecTest {

  private static final InstId INST_ID = new InstId("BTC-USDT-SWAP");

  private OkxV5WSPublicSpecImpl client;

  @BeforeEach
  void setUp() {
    client = new OkxV5WSPublicSpecImpl(new LoggingWebSocketHandler(log), 10000);
    client.start().doOnSuccess(latency -> System.out.println("Established latency:" + latency + "ms")).block();
  }

  @Test
  void candle() {
    for (Dict.CandleType type : Dict.CandleType.values()) {
      client.candle(INST_ID, type);
    }
  }

  @Test
  void instruments() throws InterruptedException {
    for (InstType type : com.g.fod.openapi.spec.domain.Dict.InstType.values()) {
      client.subInstruments(type);
    }
    TimeUnit.SECONDS.sleep(5);
  }

  @Test
  void fundingRate() {
    client.fundingRate(INST_ID);
  }

  @Test
  void status() {
    client.status();
  }

  @Test
  void books() {
    for (Dict.BookType type : Dict.BookType.values()) {
      client.books(INST_ID, type);
    }
  }

  @Test
  void ticker() {
    client.ticker(INST_ID);
  }

  @Test
  void trades() {
    client.trades(INST_ID);
  }

  @AfterEach
  void tearDown() throws InterruptedException {
    TimeUnit.SECONDS.sleep(10);
    client.close();
  }
}
