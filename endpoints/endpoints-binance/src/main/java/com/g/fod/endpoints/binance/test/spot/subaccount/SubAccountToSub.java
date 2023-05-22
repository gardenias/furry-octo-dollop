package com.g.fod.endpoints.binance.test.spot.subaccount;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubAccountToSub {

  private static final Logger logger = LoggerFactory.getLogger(SubAccountToSub.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("toEmail", "");
    parameters.put("asset", "USDT");
    parameters.put("amount", 0.01);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createSubAccount().subAccountToSubAccount(parameters);
    logger.info(result);
  }
}
