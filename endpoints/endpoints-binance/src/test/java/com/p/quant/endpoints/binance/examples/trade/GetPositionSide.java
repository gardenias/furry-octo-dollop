package com.p.quant.endpoints.binance.examples.trade;

import com.p.quant.endpoints.binance.test.usd.RequestOptions;
import com.p.quant.endpoints.binance.test.usd.SyncRequestClient;
import com.p.quant.endpoints.binance.examples.constants.PrivateConfig;

/**
 * @author : wangwanlu
 * @since : 2020/4/7, Tue
 **/
public class GetPositionSide {

    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.getPositionSide());
    }
}
