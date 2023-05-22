package com.g.fod.endpoints.binance.examples.trade;

import com.g.fod.endpoints.binance.examples.constants.PrivateConfig;
import com.g.fod.endpoints.binance.test.usd.RequestOptions;
import com.g.fod.endpoints.binance.test.usd.SyncRequestClient;

/**
 * @author : wangwanlu
 * @since : 2020/4/23, Thu
 **/
public class ChangeMarginType {

  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
      options);

    // margin type: ISOLATED, CROSSED
    System.out.println(syncRequestClient.changeMarginType("BTCUSDT", "ISOLATED"));
  }
}
