package com.p.quant.endpoints.binance.rest.websocket;

import java.util.ArrayList;

import com.p.quant.endpoints.binance.client.impl.WebsocketClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CombineStreams {
    private static final Logger logger = LoggerFactory.getLogger(CombineStreams.class);
    public static void main(String[] args) {
        WebsocketClientImpl client = new WebsocketClientImpl();
        ArrayList<String> streams = new ArrayList<>();
        streams.add("btcusdt@trade");
        streams.add("bnbusdt@trade");

        client.combineStreams(streams, ((event) -> {
            System.out.println(event);
        }));

        client.closeAllConnections();
    }
}
