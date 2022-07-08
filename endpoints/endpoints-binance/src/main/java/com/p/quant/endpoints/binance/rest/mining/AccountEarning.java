package com.p.quant.endpoints.binance.rest.mining;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountEarning {
    private static final Logger logger = LoggerFactory.getLogger(AccountEarning.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMining().accountEarning(parameters);
        logger.info(result);
    }
}
