package com.g.fod.endpoints.binance.test.usd.impl;

import java.net.URI;

import com.g.fod.endpoints.binance.test.usd.RequestOptions;
import com.g.fod.endpoints.binance.test.usd.SubscriptionClient;
import com.g.fod.endpoints.binance.test.usd.SubscriptionOptions;
import com.g.fod.endpoints.binance.test.usd.SyncRequestClient;

public final class BinanceApiInternalFactory {

  private static final BinanceApiInternalFactory instance = new BinanceApiInternalFactory();

  public static BinanceApiInternalFactory getInstance() {
    return instance;
  }

  private BinanceApiInternalFactory() {
  }

  public SyncRequestClient createSyncRequestClient(String apiKey, String secretKey, RequestOptions options) {
    RequestOptions requestOptions = new RequestOptions(options);
    RestApiRequestImpl requestImpl = new RestApiRequestImpl(apiKey, secretKey, requestOptions);
    return new SyncRequestImpl(requestImpl);
  }

  public SubscriptionClient createSubscriptionClient(SubscriptionOptions options) {
    SubscriptionOptions subscriptionOptions = new SubscriptionOptions(options);
    RequestOptions requestOptions = new RequestOptions();
    try {
      String host = new URI(options.getUri()).getHost();
      requestOptions.setUrl("https://" + host);
    } catch (Exception e) {

    }
    SubscriptionClient webSocketStreamClient = new WebSocketStreamClientImpl(subscriptionOptions);
    return webSocketStreamClient;
  }

}
