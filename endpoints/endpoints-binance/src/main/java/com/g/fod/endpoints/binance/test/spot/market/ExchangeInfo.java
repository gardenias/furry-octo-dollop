package com.g.fod.endpoints.binance.test.spot.market;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.client.impl.spot.Market;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExchangeInfo {

  private static final Logger logger = LoggerFactory.getLogger(ExchangeInfo.class);

  public static void main(String[] args) {
    SpotClientImpl client = new SpotClientImpl();
    Market market = client.createMarket();
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

    String result = market.exchangeInfo(parameters);
    logger.info(result);

    parameters.put("symbol", "BTCUSDT");
    result = market.exchangeInfo(parameters);
    logger.info(result);
    parameters.clear();

    ArrayList<String> symbols = new ArrayList<>();
    symbols.add("BTCUSDT");
    symbols.add("BNBUSDT");
    parameters.put("symbols", symbols);
    result = market.exchangeInfo(parameters);
    logger.info(result);
  }

}
