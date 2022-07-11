package com.p.quant.endpoints.binance.test.spot.savings;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DailyRedemptionQuota {
    private static final Logger logger = LoggerFactory.getLogger(DailyRedemptionQuota.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "TKO001");
        parameters.put("type", "FAST");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createSavings().dailyRedemptionQuota(parameters);
        logger.info(result);
    }
}