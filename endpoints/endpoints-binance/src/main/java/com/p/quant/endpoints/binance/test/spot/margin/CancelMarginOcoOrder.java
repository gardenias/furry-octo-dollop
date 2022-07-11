package com.p.quant.endpoints.binance.test.spot.margin;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CancelMarginOcoOrder {
    private static final Logger logger = LoggerFactory.getLogger(CancelMarginOcoOrder.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);

        parameters.put("symbol","BTCUSDT");
        parameters.put("orderListId", "");

        String result = client.createMargin().cancelOcoOrder(parameters);
        logger.info(result);
    }
}