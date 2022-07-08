package com.p.quant.endpoints.binance.rest.margin;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsolatedMarginTier {
    private static final Logger logger = LoggerFactory.getLogger(IsolatedMarginData.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol","BNBUSDT");

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().isolatedMarginData(parameters);
        logger.info(result);
    }
}
