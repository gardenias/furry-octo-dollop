package com.g.fod.endpoints.binance.test.spot.trade;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.exceptions.BinanceClientException;
import com.g.fod.endpoints.binance.client.exceptions.BinanceConnectorException;
import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetOrder {

  private static final Logger logger = LoggerFactory.getLogger(GetOrder.class);

  public static void main(String[] args) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

    SpotClientImpl client = new SpotClientImpl(PrivateConfig.TESTNET_SPOT_API_KEY,
      PrivateConfig.TESTNET_SPOT_SECRET_KEY, PrivateConfig.SPOT_BASE_URL);

    parameters.put("symbol", "BTCUSDT");
    parameters.put("orderId", "6687791");

    try {
      String result = client.createTrade().getOrder(parameters);
      logger.info(result);
    } catch (BinanceConnectorException e) {
      logger.error("fullErrMessage: {}", e.getMessage(), e);
    } catch (BinanceClientException e) {
      logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
        e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
    }
  }
}
