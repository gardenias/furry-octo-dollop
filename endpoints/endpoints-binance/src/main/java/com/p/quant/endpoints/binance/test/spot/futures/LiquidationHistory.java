package com.p.quant.endpoints.binance.test.spot.futures;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiquidationHistory {
    private static final Logger logger = LoggerFactory.getLogger(LiquidationHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createFutures().liquidationHistory(parameters);
        logger.info(result);
    }
}