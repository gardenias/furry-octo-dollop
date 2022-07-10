package com.p.quant.endpoints.binance.test.futures;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollateralRepayQuote {
    private static final Logger logger = LoggerFactory.getLogger(CollateralRepayQuote.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "USDT");
        parameters.put("collateralCoin", "BUSD");
        parameters.put("amount", 123);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createFutures().collateralRepayQuote(parameters);
        logger.info(result);
    }
}
