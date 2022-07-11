package com.p.quant.endpoints.binance.test.spot.nft;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionsHistory {
    private static final Logger logger = LoggerFactory.getLogger(TransactionsHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("orderType", 0);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createNFT().transactionsHistory(parameters);
        logger.info(result);
    }
}
