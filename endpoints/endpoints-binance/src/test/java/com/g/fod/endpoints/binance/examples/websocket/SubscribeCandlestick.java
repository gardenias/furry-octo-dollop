package com.g.fod.endpoints.binance.examples.websocket;

import com.g.fod.endpoints.binance.test.usd.SubscriptionClient;
import com.g.fod.endpoints.binance.test.usd.model.enums.CandlestickInterval;

public class SubscribeCandlestick {

  public static void main(String[] args) {

    SubscriptionClient client = SubscriptionClient.create();

    client.subscribeCandlestickEvent("btcusdt", CandlestickInterval.ONE_MINUTE, ((event) -> {
      System.out.println(event);
      client.unsubscribeAll();
    }), null);

  }

}
