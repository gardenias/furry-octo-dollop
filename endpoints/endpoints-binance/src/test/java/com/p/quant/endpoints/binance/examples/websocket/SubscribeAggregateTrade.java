package com.p.quant.endpoints.binance.examples.websocket;

import com.p.quant.endpoints.binance.test.usd.SubscriptionClient;

public class SubscribeAggregateTrade {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeAggregateTradeEvent("btcusdt", ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
