package com.p.quant.endpoints.binance.examples.websocket;

import com.p.quant.endpoints.binance.test.usd.SubscriptionClient;

public class SubscribeDiffDepth {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeDiffDepthEvent("btcusdt", ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
