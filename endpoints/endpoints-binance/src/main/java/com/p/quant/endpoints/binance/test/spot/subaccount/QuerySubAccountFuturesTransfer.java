package com.p.quant.endpoints.binance.test.spot.subaccount;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.spot.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuerySubAccountFuturesTransfer {
    private static final Logger logger = LoggerFactory.getLogger(QuerySubAccountFuturesTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "");
        parameters.put("futuresType", 1);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
        String result = client.createSubAccount().getFuturesInternalTransfer(parameters);
        logger.info(result);
    }
}
