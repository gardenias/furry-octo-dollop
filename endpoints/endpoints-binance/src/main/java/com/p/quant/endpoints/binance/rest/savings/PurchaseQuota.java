package com.p.quant.endpoints.binance.rest.savings;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PurchaseQuota {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseQuota.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "TKO001");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSavings().purchaseQuotaFlexible(parameters);
        logger.info(result);
    }
}
