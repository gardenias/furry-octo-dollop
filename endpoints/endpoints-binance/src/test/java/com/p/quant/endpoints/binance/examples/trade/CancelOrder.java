package com.p.quant.endpoints.binance.examples.trade;

import com.p.quant.endpoints.binance.test.usd.RequestOptions;
import com.p.quant.endpoints.binance.test.usd.SyncRequestClient;

import com.p.quant.endpoints.binance.examples.constants.PrivateConfig;

public class CancelOrder {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.cancelOrder("BTCUSDT", 37886301L, null));
    }
}