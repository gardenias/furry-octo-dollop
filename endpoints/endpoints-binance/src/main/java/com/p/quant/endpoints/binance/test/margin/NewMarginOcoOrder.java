package com.p.quant.endpoints.binance.test.margin;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewMarginOcoOrder {
    private static final Logger logger = LoggerFactory.getLogger(NewMarginOcoOrder.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol","BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("stopPrice", 98000);
        parameters.put("quantity", 0.01);
        parameters.put("price", 95000);

        String result = client.createMargin().ocoOrder(parameters);
        logger.info(result);
    }
}
