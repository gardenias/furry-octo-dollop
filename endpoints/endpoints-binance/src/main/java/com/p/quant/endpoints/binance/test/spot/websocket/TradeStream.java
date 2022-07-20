package com.p.quant.endpoints.binance.test.spot.websocket;

import com.p.quant.endpoints.binance.client.impl.WebsocketClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeStream {
    private static final Logger logger = LoggerFactory.getLogger(TradeStream.class);
    public static void main(String[] args) {
        WebsocketClientImpl client = new WebsocketClientImpl();
        client.tradeStream("btcusdt",((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
