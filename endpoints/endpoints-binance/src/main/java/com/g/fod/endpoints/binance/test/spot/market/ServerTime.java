package com.g.fod.endpoints.binance.test.spot.market;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerTime {

  private static final Logger logger = LoggerFactory.getLogger(ServerTime.class);

  public static void main(String[] args) {

    SpotClientImpl client = new SpotClientImpl();
    String result = client.createMarket().time();
    logger.info(result);
  }
}
