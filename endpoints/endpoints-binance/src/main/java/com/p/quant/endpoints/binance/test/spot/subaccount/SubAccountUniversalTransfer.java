package com.p.quant.endpoints.binance.test.spot.subaccount;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubAccountUniversalTransfer {
    private static final Logger logger = LoggerFactory.getLogger(SubAccountUniversalTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("fromAccountType", "SPOT");
        parameters.put("toAccountType", "USDT_FUTURE");
        parameters.put("asset", "USDT");
        parameters.put("amount", 0.01);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createSubAccount().universalTransfer(parameters);
        logger.info(result);
    }
}
