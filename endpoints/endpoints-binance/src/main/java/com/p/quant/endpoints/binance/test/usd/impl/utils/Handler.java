package com.p.quant.endpoints.binance.test.usd.impl.utils;

@FunctionalInterface
public interface Handler<T> {

  void handle(T t);
}
