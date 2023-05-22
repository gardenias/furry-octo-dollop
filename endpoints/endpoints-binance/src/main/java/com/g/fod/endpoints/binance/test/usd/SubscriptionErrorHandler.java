package com.g.fod.endpoints.binance.test.usd;

import com.g.fod.endpoints.binance.test.usd.exception.BinanceApiException;

/**
 * The error handler for the subscription.
 */
@FunctionalInterface
public interface SubscriptionErrorHandler {

  void onError(BinanceApiException exception);
}
