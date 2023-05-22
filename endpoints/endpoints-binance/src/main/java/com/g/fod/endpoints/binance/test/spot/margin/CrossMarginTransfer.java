package com.g.fod.endpoints.binance.test.spot.margin;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrossMarginTransfer {

  private static final Logger logger = LoggerFactory.getLogger(CrossMarginTransfer.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("asset", "USDT");
    parameters.put("amount", 1);
    parameters.put("type", 1);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createMargin().crossMarginTransfer(parameters);
    logger.info(result);
  }
}
