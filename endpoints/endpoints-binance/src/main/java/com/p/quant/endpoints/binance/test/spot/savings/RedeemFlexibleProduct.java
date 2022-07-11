package com.p.quant.endpoints.binance.test.spot.savings;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedeemFlexibleProduct {
    private static final Logger logger = LoggerFactory.getLogger(RedeemFlexibleProduct.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "TKO001");
        parameters.put("amount", 0.01);
        parameters.put("type", "FAST");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createSavings().redeemFlexibleProduct(parameters);
        logger.info(result);
    }
}
