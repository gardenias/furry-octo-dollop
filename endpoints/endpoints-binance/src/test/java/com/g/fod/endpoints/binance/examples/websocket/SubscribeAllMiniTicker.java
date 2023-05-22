package com.g.fod.endpoints.binance.examples.websocket;

import com.g.fod.endpoints.binance.test.usd.SubscriptionClient;

public class SubscribeAllMiniTicker {

  public static void main(String[] args) {

    SubscriptionClient client = SubscriptionClient.create();

    client.subscribeAllMiniTickerEvent(System.out::println, null);

  }

}
