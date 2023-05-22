package com.g.fod.endpoints.binance.examples.websocket;

import com.g.fod.endpoints.binance.test.usd.SubscriptionClient;

public class SubscribeMarkPrice {

  public static void main(String[] args) {

    SubscriptionClient client = SubscriptionClient.create();

    client.subscribeMarkPriceEvent("btcusdt", ((event) -> {
      System.out.println(event);
      client.unsubscribeAll();
    }), null);

  }

}
