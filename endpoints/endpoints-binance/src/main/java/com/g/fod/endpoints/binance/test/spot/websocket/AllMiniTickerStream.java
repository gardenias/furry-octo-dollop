package com.g.fod.endpoints.binance.test.spot.websocket;

import com.g.fod.endpoints.binance.client.impl.WebsocketClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllMiniTickerStream {

  private static final Logger logger = LoggerFactory.getLogger(AllMiniTickerStream.class);

  public static void main(String[] args) {
    WebsocketClientImpl client = new WebsocketClientImpl();
    client.allMiniTickerStream(((event) -> {
      System.out.println(event);
      client.closeAllConnections();
    }));
  }
}
