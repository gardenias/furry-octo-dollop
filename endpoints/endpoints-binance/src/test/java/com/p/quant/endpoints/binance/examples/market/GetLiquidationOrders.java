package com.p.quant.endpoints.binance.examples.market;

import com.p.quant.endpoints.binance.test.usd.RequestOptions;
import com.p.quant.endpoints.binance.test.usd.SyncRequestClient;

import com.p.quant.endpoints.binance.examples.constants.PrivateConfig;

public class GetLiquidationOrders {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.getLiquidationOrders("BTCUSDT", null, null, null));
    }
}
