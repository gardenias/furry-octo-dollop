package com.p.quant.endpoints.binance.test.market;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TickerSymbol {
    private static final Logger logger = LoggerFactory.getLogger(TickerSymbol.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol","BNBUSDT");
        String result = client.createMarket().tickerSymbol(parameters);
        logger.info(result);
    }
}
