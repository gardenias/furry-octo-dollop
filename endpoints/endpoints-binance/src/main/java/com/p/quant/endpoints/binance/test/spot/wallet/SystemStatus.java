package com.p.quant.endpoints.binance.test.spot.wallet;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemStatus {
    private static final Logger logger = LoggerFactory.getLogger(SystemStatus.class);
    public static void main(String[] args) {

        SpotClientImpl client = new SpotClientImpl();
        String result = client.createWallet().systemStatus();
        logger.info(result);
    }
}
