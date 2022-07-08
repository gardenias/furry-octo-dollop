package com.p.quant.endpoints.binance.rest.market;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Klines {
    private static final Logger logger = LoggerFactory.getLogger(Klines.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol","BNBUSDT");
        parameters.put("interval", "1m");

        String result = client.createMarket().klines(parameters);
        logger.info(result);
    }
}
