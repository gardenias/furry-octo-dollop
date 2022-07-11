package com.p.quant.endpoints.binance.test.spot.subaccount;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubAccountFuturesTransfer {
    private static final Logger logger = LoggerFactory.getLogger(SubAccountFuturesTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "");
        parameters.put("asset", "USDT");
        parameters.put("amount", 100);
        parameters.put("type", 2);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createSubAccount().futuresTransfer(parameters);
        logger.info(result);
    }
}
