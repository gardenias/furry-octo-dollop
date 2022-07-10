package com.p.quant.endpoints.binance.test.wallet;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountSnapshot {
    private static final Logger logger = LoggerFactory.getLogger(AccountSnapshot.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "SPOT");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createWallet().accountSnapshot(parameters);
        logger.info(result);
    }
}