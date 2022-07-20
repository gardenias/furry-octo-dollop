package com.p.quant.endpoints.binance.test.spot.websocket;

import com.p.quant.endpoints.binance.client.impl.WebsocketClientImpl;
import com.p.quant.endpoints.binance.client.utils.WebSocketCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeStreamWithAllCallbacks {

    private static final Logger logger = LoggerFactory.getLogger(TradeStreamWithAllCallbacks.class);

    private static volatile boolean isTradeStreamUp = false;
    private static WebSocketCallback onOpenCallback;
    private static WebSocketCallback onMessageCallback;
    private static WebSocketCallback onClosingCallback;
    private static WebSocketCallback onFailureCallback;

    public static void main(String[] args) {
        WebsocketClientImpl client = new WebsocketClientImpl();
        onOpenCallback = openEvent -> {
            isTradeStreamUp = true;
        };
        onMessageCallback = (message) -> {
            System.out.println(message);
            client.closeAllConnections();
        };
        onClosingCallback = closingEvent -> {
            isTradeStreamUp = false;
        };
        onFailureCallback = failureEvent -> {
            isTradeStreamUp = false;
            connectToTradeStream(client, onOpenCallback, onMessageCallback, onClosingCallback, onClosingCallback);
        };
        connectToTradeStream(client, onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback);
    }

    private static void connectToTradeStream(
            WebsocketClientImpl client,
            WebSocketCallback onOpenCallback,
            WebSocketCallback onMessageCallback,
            WebSocketCallback onClosingCallback,
            WebSocketCallback onFailureCallback) {
        client.tradeStream("btcusdt", onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback);
    }
}
