package com.g.fod.endpoints.binance.test.spot.blvt;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Redeem {

  private static final Logger logger = LoggerFactory.getLogger(Redeem.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("tokenName", "BTCDOWN");
    parameters.put("amount", 0.01);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createBlvt().redeem(parameters);
    logger.info(result);
  }
}
