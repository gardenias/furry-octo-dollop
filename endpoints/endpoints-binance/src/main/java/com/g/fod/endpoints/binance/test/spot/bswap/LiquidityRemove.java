package com.g.fod.endpoints.binance.test.spot.bswap;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiquidityRemove {

  private static final Logger logger = LoggerFactory.getLogger(LiquidityRemove.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("poolId", 32L);
    parameters.put("type", "COMBINATION");
    parameters.put("shareAmount", 0.1);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createBswap().liquidityRemove(parameters);
    logger.info(result);
  }
}
