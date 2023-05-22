package com.g.fod.endpoints.binance.examples.websocket;

import com.g.fod.endpoints.binance.test.usd.SubscriptionClient;

public class SubscribeSymbolLiquidationOrder {

  public static void main(String[] args) {

    SubscriptionClient client = SubscriptionClient.create();

    client.subscribeSymbolLiquidationOrderEvent("btcusdt", ((event) -> {
      System.out.println(event);
      client.unsubscribeAll();
    }), null);

  }

}
