package com.g.fod.endpoints.binance.test.spot.userdata;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateIsolatedListenKey {

  private static final Logger logger = LoggerFactory.getLogger(CreateIsolatedListenKey.class);

  public static void main(String[] args) {
    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("symbol", "BTCUSDT");

    String result = client.createUserData().createIsloatedListenKey(parameters);
    logger.info(result);
  }
}
