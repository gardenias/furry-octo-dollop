package com.g.fod.endpoints.binance.test.spot.wallet;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DustTransfer {

  private static final Logger logger = LoggerFactory.getLogger(DustTransfer.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    ArrayList<String> assets = new ArrayList<>();
    assets.add("CHR");
    assets.add("CTSI");
    parameters.put("asset", assets);

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    String result = client.createWallet().dustTransfer(parameters);
    logger.info(result);
  }
}
