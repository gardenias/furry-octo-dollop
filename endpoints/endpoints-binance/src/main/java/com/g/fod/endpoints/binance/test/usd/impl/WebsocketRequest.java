package com.g.fod.endpoints.binance.test.usd.impl;

import com.g.fod.endpoints.binance.test.usd.impl.utils.Handler;
import com.g.fod.endpoints.binance.test.usd.SubscriptionErrorHandler;
import com.g.fod.endpoints.binance.test.usd.SubscriptionListener;

class WebsocketRequest<T> {

  WebsocketRequest(SubscriptionListener<T> listener, SubscriptionErrorHandler errorHandler) {
    this.updateCallback = listener;
    this.errorHandler = errorHandler;
  }

  String signatureVersion = "2";
  String name;
  Handler<WebSocketConnection> connectionHandler;
  Handler<WebSocketConnection> authHandler = null;
  final SubscriptionListener<T> updateCallback;
  RestApiJsonParser<T> jsonParser;
  final SubscriptionErrorHandler errorHandler;
}
