package com.p.quant.endpoints.binance.rest.market;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Depth {
    private static final Logger logger = LoggerFactory.getLogger(Depth.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol","BNBUSDT");
        String result = client.createMarket().depth(parameters);
        logger.info(result);
    }
}
