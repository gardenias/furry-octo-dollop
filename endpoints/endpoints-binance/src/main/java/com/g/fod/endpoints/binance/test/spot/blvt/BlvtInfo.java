package com.g.fod.endpoints.binance.test.spot.blvt;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.SpotClient;
import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlvtInfo {

  private static final Logger logger = LoggerFactory.getLogger(BlvtInfo.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

    SpotClient client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createBlvt().blvtInfo(parameters);
    logger.info(result);
  }
}
