package com.p.quant.endpoints.binance.test.spot.convert;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeFlow {
    private static final Logger logger = LoggerFactory.getLogger(TradeFlow.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        parameters.put("startTime",1234567L);
        parameters.put("endTime",12345678L);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createConvert().tradeFlow(parameters);
        logger.info(result);
    }
}
