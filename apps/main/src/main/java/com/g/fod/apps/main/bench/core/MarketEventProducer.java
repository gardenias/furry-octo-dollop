package com.g.fod.apps.main.bench.core;

public interface MarketEventProducer {

  void start() throws Exception;

  void close() throws Exception;

  boolean isActive();
}
