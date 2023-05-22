package com.g.fod.endpoints.binance.test.spot.mining;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BonusList {

  private static final Logger logger = LoggerFactory.getLogger(BonusList.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("algo", "sha256");
    parameters.put("userName", "");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createMining().bonusList(parameters);
    logger.info(result);
  }
}
