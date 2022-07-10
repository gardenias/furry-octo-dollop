package com.p.quant.endpoints.binance.test.websocket;

import com.p.quant.endpoints.binance.client.enums.DefaultUrls;
import com.p.quant.endpoints.binance.client.impl.SpotClientImpl;
import com.p.quant.endpoints.binance.client.impl.WebsocketClientImpl;

import com.p.quant.endpoints.binance.test.PrivateConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserStream {
    private static final Logger logger = LoggerFactory.getLogger(UserStream.class);
    public static void main(String[] args) {
        WebsocketClientImpl wsClient = new WebsocketClientImpl(DefaultUrls.TESTNET_WSS_URL);
        SpotClientImpl spotClient = new SpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, DefaultUrls.TESTNET_URL);
        JSONObject obj = new JSONObject(spotClient.createUserData().createListenKey());
        String listenKey = obj.getString("listenKey");
        System.out.println("listenKey:" + listenKey);
        wsClient.listenUserStream(listenKey, ((event) -> {
            System.out.println(event);
            wsClient.closeAllConnections();
        }));
    }
}