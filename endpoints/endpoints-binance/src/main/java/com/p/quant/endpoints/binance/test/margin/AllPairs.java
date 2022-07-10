package com.p.quant.endpoints.binance.test.margin;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllPairs {
    private static final Logger logger = LoggerFactory.getLogger(AllPairs.class);
    public static void main(String[] args) {
        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().allPairs();
        logger.info(result);
    }
}
