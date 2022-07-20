package com.p.quant.endpoints.binance.rest.subaccount;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MangedSubDeposit {
    private static final Logger logger = LoggerFactory.getLogger(MangedSubDeposit.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "");
        parameters.put("asset", "USDT");
        parameters.put("amount", 0.01);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().managedSubDeposit(parameters);
        logger.info(result);
    }
}
