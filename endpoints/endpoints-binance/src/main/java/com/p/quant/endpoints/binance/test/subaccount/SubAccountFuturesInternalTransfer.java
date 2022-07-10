package com.p.quant.endpoints.binance.test.subaccount;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubAccountFuturesInternalTransfer {
    private static final Logger logger = LoggerFactory.getLogger(SubAccountFuturesInternalTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("fromEmail", "");
        parameters.put("toEmail", "");
        parameters.put("futuresType", 1);
        parameters.put("asset", "USDT");
        parameters.put("amount", 0.01);

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().futuresInternalTransfer(parameters);
        logger.info(result);
    }
}
