package com.p.quant.endpoints.binance.test.spot.bswap;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quote {
    private static final Logger logger = LoggerFactory.getLogger(Quote.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("quoteAsset", "USDT");
        parameters.put("baseAsset", "BUSD");
        parameters.put("quoteQty", 10000);


        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createBswap().quote(parameters);
        logger.info(result);
    }
}
