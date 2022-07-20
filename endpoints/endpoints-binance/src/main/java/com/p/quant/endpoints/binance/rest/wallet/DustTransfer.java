package com.p.quant.endpoints.binance.rest.wallet;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DustTransfer {
    private static final Logger logger = LoggerFactory.getLogger(DustTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        ArrayList<String> assets = new ArrayList<>();
        assets.add("CHR");
        assets.add("CTSI");
        parameters.put("asset", assets);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createWallet().dustTransfer(parameters);
        logger.info(result);
    }
}
