package com.p.quant.endpoints.binance.rest.margin;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllAssets {
    private static final Logger logger = LoggerFactory.getLogger(AllAssets.class);
    public static void main(String[] args) {
        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().allAssets();
        logger.info(result);
    }
}
