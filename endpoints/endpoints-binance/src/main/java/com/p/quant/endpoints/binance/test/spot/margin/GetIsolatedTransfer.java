package com.p.quant.endpoints.binance.test.spot.margin;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetIsolatedTransfer {
    private static final Logger logger = LoggerFactory.getLogger(GetIsolatedTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);

        parameters.put("symbol","BNBUSDT");

        String result = client.createMargin().getIsolatedTransfer(parameters);
        logger.info(result);
    }
}
