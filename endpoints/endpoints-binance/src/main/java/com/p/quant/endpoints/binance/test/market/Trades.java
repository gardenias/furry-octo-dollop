package com.p.quant.endpoints.binance.test.market;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trades {
    private static final Logger logger = LoggerFactory.getLogger(Trades.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl();

        parameters.put("symbol","BNBUSDT");
        String result = client.createMarket().trades(parameters);
        logger.info(result);
    }
}
