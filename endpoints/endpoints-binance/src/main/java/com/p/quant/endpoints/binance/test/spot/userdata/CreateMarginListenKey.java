package com.p.quant.endpoints.binance.test.spot.userdata;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateMarginListenKey {
    private static final Logger logger = LoggerFactory.getLogger(CreateMarginListenKey.class);
    public static void main(String[] args) {
        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);

        String result = client.createUserData().createMarginListenKey();
        logger.info(result);
    }
}