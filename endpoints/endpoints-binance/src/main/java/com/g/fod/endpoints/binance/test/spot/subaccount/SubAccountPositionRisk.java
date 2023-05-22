package com.g.fod.endpoints.binance.test.spot.subaccount;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubAccountPositionRisk {

  private static final Logger logger = LoggerFactory.getLogger(SubAccountPositionRisk.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("email", "another_virtual@q6c1dsmxnoemail.com");

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createSubAccount().futuresPositionRisk(parameters);
    logger.info(result);
  }
}
