package com.p.quant.endpoints.binance.examples.websocket;

import com.p.quant.endpoints.binance.test.usd.SubscriptionClient;

public class SubscribeBookDepth {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeBookDepthEvent("btcusdt", 5, ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
