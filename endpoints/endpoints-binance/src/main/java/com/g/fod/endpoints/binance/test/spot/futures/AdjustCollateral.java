package com.g.fod.endpoints.binance.test.spot.futures;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdjustCollateral {

  private static final Logger logger = LoggerFactory.getLogger(AdjustCollateral.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("loanCoin", "USDT");
    parameters.put("collateralCoin", "BUSD");
    parameters.put("amount", 2);
    parameters.put("direction", "ADDITIONAL");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createFutures().adjustCollateral(parameters);
    logger.info(result);
  }
}
