package com.p.quant.endpoints.binance.rest.userdata;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseIsolatedListenKey {
    private static final Logger logger = LoggerFactory.getLogger(CloseIsolatedListenKey.class);
    public static void main(String[] args) {
        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("listenKey","");

        String result = client.createUserData().closeIsloatedListenKey(parameters);
        logger.info(result);
    }
}
