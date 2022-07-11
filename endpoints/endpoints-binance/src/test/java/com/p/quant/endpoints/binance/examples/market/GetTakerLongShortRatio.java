package com.p.quant.endpoints.binance.examples.market;

import com.p.quant.endpoints.binance.test.usd.RequestOptions;
import com.p.quant.endpoints.binance.test.usd.SyncRequestClient;
import com.p.quant.endpoints.binance.examples.constants.PrivateConfig;
import com.p.quant.endpoints.binance.test.usd.model.enums.PeriodType;

public class GetTakerLongShortRatio {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.getTakerLongShortRatio("BTCUSDT", PeriodType._5m,null,null,10));


    }
}
