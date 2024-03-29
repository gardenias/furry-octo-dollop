package com.g.fod.endpoints.binance.test.spot.margin;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewMarginOrder {

  private static final Logger logger = LoggerFactory.getLogger(NewMarginOrder.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("symbol", "BNBUSDT");
    parameters.put("side", "SELL");
    parameters.put("type", "MARKET");
    parameters.put("quantity", 0.1);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createMargin().newOrder(parameters);
    logger.info(result);
  }
}
