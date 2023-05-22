package com.g.fod.endpoints.binance.client.utils;

import okhttp3.OkHttpClient;

public class HttpClientSingleton {

  private static final OkHttpClient httpClient = new OkHttpClient();

  private HttpClientSingleton() {
  }

  public static final OkHttpClient getHttpClient() {
    return httpClient;
  }
}
