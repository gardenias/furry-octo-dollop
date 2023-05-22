package com.g.fod.endpoints.binance.test.spot.market;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HistoricalTrades {

  private static final Logger logger = LoggerFactory.getLogger(HistoricalTrades.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, null);

    parameters.put("symbol", "BTCUSDT");
    parameters.put("limit", "10");
    String result = client.createMarket().historicalTrades(parameters);
    logger.info(result);
  }
}
