package com.p.quant.endpoints.binance.rest.wallet;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Withdraw {
    private static final Logger logger = LoggerFactory.getLogger(Withdraw.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BNBUSDT");
        parameters.put("address", "");
        parameters.put("amount", "0.001");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createWallet().withdraw(parameters);
        logger.info(result);
    }
}
