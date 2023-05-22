package com.g.fod.endpoints.binance.test.spot.market;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AggTrades {

  private static final Logger logger = LoggerFactory.getLogger(AggTrades.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);

    parameters.put("symbol", "BNBUSDT");
    String result = client.createMarket().aggTrades(parameters);
    logger.info(result);
  }
}
