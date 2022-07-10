package com.p.quant.endpoints.binance.test.margin;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsolatedTransfer {
    private static final Logger logger = LoggerFactory.getLogger(IsolatedTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol","BNBUSDT");
        parameters.put("asset", "USDT");
        parameters.put("transFrom", "SPOT");
        parameters.put("transTo", "ISOLATED_MARGIN");
        parameters.put("amount", 1);

        String result = client.createMargin().isolatedTransfer(parameters);
        logger.info(result);
    }
}
