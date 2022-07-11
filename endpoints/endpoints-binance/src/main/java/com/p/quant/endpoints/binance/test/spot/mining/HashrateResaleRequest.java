package com.p.quant.endpoints.binance.test.spot.mining;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashrateResaleRequest {
    private static final Logger logger = LoggerFactory.getLogger(HashrateResaleRequest.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "test");
        parameters.put("algo", "sha256");
        parameters.put("endDate", 1617659086000L);
        parameters.put("startDate", 1607659086000L);
        parameters.put("toPoolUser", "S19pro");
        parameters.put("hashRate", 100000000L);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createMining().hashrateResaleRequest(parameters);
        logger.info(result);
    }
}
