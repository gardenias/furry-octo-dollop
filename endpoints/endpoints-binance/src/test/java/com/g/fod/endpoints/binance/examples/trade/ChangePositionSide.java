package com.g.fod.endpoints.binance.examples.trade;

import com.g.fod.endpoints.binance.examples.constants.PrivateConfig;
import com.g.fod.endpoints.binance.test.usd.RequestOptions;
import com.g.fod.endpoints.binance.test.usd.SyncRequestClient;

/**
 * @author : wangwanlu
 * @since : 2020/3/25, Wed
 **/
public class ChangePositionSide {

  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
      options);
    System.out.println(syncRequestClient.changePositionSide(true));
  }
}
