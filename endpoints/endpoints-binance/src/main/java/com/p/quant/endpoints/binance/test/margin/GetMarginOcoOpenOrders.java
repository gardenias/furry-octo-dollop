package com.p.quant.endpoints.binance.test.margin;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetMarginOcoOpenOrders {
    private static final Logger logger = LoggerFactory.getLogger(GetMarginOcoOpenOrders.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().getOcoOpenOrders(parameters);
        logger.info(result);
    }
}