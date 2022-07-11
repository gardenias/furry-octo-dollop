package com.p.quant.endpoints.binance.test.usd.impl;

import com.p.quant.endpoints.binance.test.usd.impl.utils.JsonWrapper;

@FunctionalInterface
public interface RestApiJsonParser<T> {

  T parseJson(JsonWrapper json);
}
