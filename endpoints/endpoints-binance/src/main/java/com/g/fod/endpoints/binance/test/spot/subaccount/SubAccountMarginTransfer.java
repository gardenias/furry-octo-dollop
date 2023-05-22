package com.g.fod.endpoints.binance.test.spot.subaccount;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubAccountMarginTransfer {

  private static final Logger logger = LoggerFactory.getLogger(SubAccountMarginTransfer.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("email", "");
    parameters.put("asset", "USDT");
    parameters.put("amount", 0.01);
    parameters.put("type", 2);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createSubAccount().marginTransfer(parameters);
    logger.info(result);
  }
}
