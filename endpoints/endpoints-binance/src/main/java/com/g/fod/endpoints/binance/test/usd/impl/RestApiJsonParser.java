package com.g.fod.endpoints.binance.test.usd.impl;

import com.g.fod.endpoints.binance.test.usd.impl.utils.JsonWrapper;

@FunctionalInterface
public interface RestApiJsonParser<T> {

  T parseJson(JsonWrapper json);
}
