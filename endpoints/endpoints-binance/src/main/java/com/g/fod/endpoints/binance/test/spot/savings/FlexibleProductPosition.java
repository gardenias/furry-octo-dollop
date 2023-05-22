package com.g.fod.endpoints.binance.test.spot.savings;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlexibleProductPosition {

  private static final Logger logger = LoggerFactory.getLogger(FlexibleProductPosition.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("asset", "BNB");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createSavings().flexibleProductPosition(parameters);
    logger.info(result);
  }
}
