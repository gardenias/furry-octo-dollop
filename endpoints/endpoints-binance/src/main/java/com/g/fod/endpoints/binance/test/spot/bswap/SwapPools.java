package com.g.fod.endpoints.binance.test.spot.bswap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwapPools {

  private static final Logger logger = LoggerFactory.getLogger(SwapPools.class);

  public static void main(String[] args) {
    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createBswap().swapPools();
    logger.info(result);
  }
}
