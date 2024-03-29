package com.g.fod.endpoints.binance.test.spot.c2c;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUserOrderHistory {

  private static final Logger logger = LoggerFactory.getLogger(ListUserOrderHistory.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("tradeType", "BUY");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createC2C().listUserOrderHistory(parameters);
    logger.info(result);
  }
}
