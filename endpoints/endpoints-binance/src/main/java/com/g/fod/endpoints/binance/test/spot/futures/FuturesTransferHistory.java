package com.g.fod.endpoints.binance.test.spot.futures;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FuturesTransferHistory {

  private static final Logger logger = LoggerFactory.getLogger(FuturesTransferHistory.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("asset", "USDT");
    parameters.put("startTime", 0L);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createFutures().futuresTransferHistory(parameters);
    logger.info(result);
  }
}
