package com.g.fod.endpoints.binance.test.spot.margin;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllAssets {

  private static final Logger logger = LoggerFactory.getLogger(AllAssets.class);

  public static void main(String[] args) {
    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createMargin().allAssets();
    logger.info(result);
  }
}
