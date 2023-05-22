package com.g.fod.endpoints.binance.test.spot.mining;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashrateResaleDetail {

  private static final Logger logger = LoggerFactory.getLogger(HashrateResaleDetail.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("configId", 123);
    parameters.put("userName", "");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createMining().hashrateResaleDetail(parameters);
    logger.info(result);
  }
}
