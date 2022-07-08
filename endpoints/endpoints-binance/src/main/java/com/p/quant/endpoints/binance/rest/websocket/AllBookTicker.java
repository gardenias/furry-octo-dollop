package com.p.quant.endpoints.binance.rest.websocket;

import com.p.quant.endpoints.binance.client.impl.WebsocketClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllBookTicker {
    private static final Logger logger = LoggerFactory.getLogger(AllBookTicker.class);
    public static void main(String[] args) {
        WebsocketClientImpl client = new WebsocketClientImpl();
        client.allBookTickerStream(((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
