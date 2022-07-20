package com.p.quant.endpoints.binance.test.websocket;

import com.p.quant.endpoints.binance.client.impl.WebsocketClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllMiniTickerStream {
    private static final Logger logger = LoggerFactory.getLogger(AllMiniTickerStream.class);
    public static void main(String[] args) {
        WebsocketClientImpl client = new WebsocketClientImpl();
        client.allMiniTickerStream(((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
