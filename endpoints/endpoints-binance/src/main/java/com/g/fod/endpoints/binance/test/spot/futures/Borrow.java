package com.g.fod.endpoints.binance.test.spot.futures;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Borrow {

  private static final Logger logger = LoggerFactory.getLogger(Borrow.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("coin", "USDT");
    parameters.put("amount", 0.01);
    parameters.put("collateralCoin", "BUSD");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createFutures().borrow(parameters);
    logger.info(result);
  }
}
