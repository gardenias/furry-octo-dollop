package com.g.fod.endpoints.binance.examples.user;

import com.g.fod.endpoints.binance.examples.constants.PrivateConfig;
import com.g.fod.endpoints.binance.test.usd.RequestOptions;
import com.g.fod.endpoints.binance.test.usd.SubscriptionClient;
import com.g.fod.endpoints.binance.test.usd.SyncRequestClient;

public class SubscribeUserData {

  public static void main(String[] args) {

    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
      options);

    // Start user data stream
    String listenKey = syncRequestClient.startUserDataStream();
    System.out.println("listenKey: " + listenKey);

    // Keep user data stream
    syncRequestClient.keepUserDataStream(listenKey);

    // Close user data stream
    syncRequestClient.closeUserDataStream(listenKey);

    SubscriptionClient client = SubscriptionClient.create();

    client.subscribeUserDataEvent(listenKey, System.out::println, null);

  }

}
