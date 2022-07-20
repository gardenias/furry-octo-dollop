package com.p.quant.endpoints.binance.rest.trade;

import java.util.LinkedHashMap;

import com.p.quant.endpoints.binance.client.exceptions.BinanceClientException;
import com.p.quant.endpoints.binance.client.exceptions.BinanceConnectorException;
import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.rest.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewOrder {
    private static final Logger logger = LoggerFactory.getLogger(NewOrder.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        SpotClientImpl client = new SpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, PrivateConfig.BASE_URL);

        parameters.put("symbol","BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("type", "LIIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", 0.01);
        parameters.put("price", 95000);

        try {
            String result = client.createTrade().newOrder(parameters);
            logger.info(result);
        }
        catch (BinanceConnectorException e) {
            logger.error("fullErrMessage: {}", e.getMessage(), e);
        }
        catch (BinanceClientException e) {
            logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
        }
    }
}
