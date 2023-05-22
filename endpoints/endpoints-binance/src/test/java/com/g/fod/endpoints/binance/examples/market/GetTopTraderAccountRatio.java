package com.g.fod.endpoints.binance.examples.market;

import com.g.fod.endpoints.binance.examples.constants.PrivateConfig;
import com.g.fod.endpoints.binance.test.usd.RequestOptions;
import com.g.fod.endpoints.binance.test.usd.SyncRequestClient;
import com.g.fod.endpoints.binance.test.usd.model.enums.PeriodType;

public class GetTopTraderAccountRatio {

  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
      options);
    System.out.println(syncRequestClient.getTopTraderAccountRatio("BTCUSDT", PeriodType._5m, null, null, 10));

  }
}
