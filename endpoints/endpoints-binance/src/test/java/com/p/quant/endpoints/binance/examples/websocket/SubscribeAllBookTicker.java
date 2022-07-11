package com.p.quant.endpoints.binance.examples.websocket;

import com.p.quant.endpoints.binance.test.usd.SubscriptionClient;

public class SubscribeAllBookTicker {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeAllBookTickerEvent(System.out::println, null);

    }

}
