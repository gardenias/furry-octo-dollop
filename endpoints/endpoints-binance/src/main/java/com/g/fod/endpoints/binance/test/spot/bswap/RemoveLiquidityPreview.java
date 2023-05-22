package com.g.fod.endpoints.binance.test.spot.bswap;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveLiquidityPreview {

  private static final Logger logger = LoggerFactory.getLogger(RemoveLiquidityPreview.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("poolId", 2L);
    parameters.put("type", "COMBINATION");
    parameters.put("quoteAsset", "USDT");
    parameters.put("shareAmount", 10000);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createBswap().removeLiquidityPreview(parameters);
    logger.info(result);
  }
}
