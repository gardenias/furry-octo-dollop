package com.p.quant.endpoints.binance.test.spot.margin;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BnbBurn {
    private static final Logger logger = LoggerFactory.getLogger(BnbBurn.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("spotBNBBurn", true);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createMargin().bnbBurn(parameters);
        logger.info(result);
    }
}