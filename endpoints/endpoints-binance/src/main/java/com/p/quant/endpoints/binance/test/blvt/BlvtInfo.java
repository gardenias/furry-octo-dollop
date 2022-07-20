package com.p.quant.endpoints.binance.test.blvt;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.SpotClient;
import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlvtInfo {
    private static final Logger logger = LoggerFactory.getLogger(BlvtInfo.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBlvt().blvtInfo(parameters);
        logger.info(result);
    }
}
