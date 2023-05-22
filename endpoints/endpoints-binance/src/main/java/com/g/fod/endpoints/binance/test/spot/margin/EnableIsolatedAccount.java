package com.g.fod.endpoints.binance.test.spot.margin;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnableIsolatedAccount {

  private static final Logger logger = LoggerFactory.getLogger(EnableIsolatedAccount.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);

    parameters.put("symbol", "BNBUSDT");

    String result = client.createMargin().enableIsolatedAccount(parameters);
    logger.info(result);
  }
}
