package com.p.quant.endpoints.binance.test.spot.fiat;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Payments {
    private static final Logger logger = LoggerFactory.getLogger(Payments.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("transactionType", "0");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createFiat().payments(parameters);
        logger.info(result);
    }
}
