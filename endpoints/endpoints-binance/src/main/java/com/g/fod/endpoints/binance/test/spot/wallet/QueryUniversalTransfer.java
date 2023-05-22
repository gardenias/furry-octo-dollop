package com.g.fod.endpoints.binance.test.spot.wallet;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryUniversalTransfer {

  private static final Logger logger = LoggerFactory.getLogger(QueryUniversalTransfer.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("type", "MAIN_MARGIN");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createWallet().queryUniversalTransfer(parameters);
    logger.info(result);
  }
}
