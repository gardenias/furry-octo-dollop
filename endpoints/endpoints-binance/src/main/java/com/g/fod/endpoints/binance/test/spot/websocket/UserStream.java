package com.g.fod.endpoints.binance.test.spot.websocket;

import com.g.fod.endpoints.binance.client.enums.DefaultUrls;
import com.g.fod.endpoints.binance.client.impl.SpotClientImpl;
import com.g.fod.endpoints.binance.client.impl.WebsocketClientImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserStream {

  private static final Logger logger = LoggerFactory.getLogger(UserStream.class);

  public static void main(String[] args) {
    WebsocketClientImpl wsClient = new WebsocketClientImpl(DefaultUrls.TESTNET_WSS_URL);
    SpotClientImpl spotClient = new SpotClientImpl(PrivateConfig.TESTNET_SPOT_API_KEY,
      PrivateConfig.TESTNET_SPOT_SECRET_KEY, DefaultUrls.TESTNET_URL);
    JSONObject obj = new JSONObject(spotClient.createUserData().createListenKey());
    String listenKey = obj.getString("listenKey");
    System.out.println("listenKey:" + listenKey);
    wsClient.listenUserStream(listenKey, ((event) -> {
      System.out.println(event);
      wsClient.closeAllConnections();
    }));
  }
}
