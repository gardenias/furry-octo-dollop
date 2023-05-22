package com.g.fod.endpoints.binance;

import com.g.fod.endpoints.binance.common.BinanceSignatureProducer;
import com.g.fod.endpoints.binance.spec.BinanceMarketRestSpec;
import com.g.fod.endpoints.binance.spec.BinanceTradeRestSpec;
import com.g.fod.endpoints.binance.spec.impl.BinanceMarketRestSpecImpl;
import com.g.fod.endpoints.binance.spec.impl.BinanceTradeRestSpecImpl;
import com.g.fod.endpoints.binance.test.spot.PrivateConfig;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author ：wanghao
 * @date ：Created in 2022/7/11 16:54
 * @description：
 * @modified By：
 * @version: $
 */
public class CommParams {

  public final static BinanceSignatureProducer signatureSpotProducer;
  //    public final static BinanceSignatureProducer signatureUsdProducer;
  public final static BinanceSignatureProducer signatureSpotProducerTest;
  public final static BinanceSignatureProducer signatureUsdProducerTest;
  public final static WebClient webClientSpot;
  //    public final static WebClient webClientUsd;
  public final static WebClient webClientTestSpot;
  public final static WebClient webClientTestUsd;
  public final static BinanceTradeRestSpec tradeRestSpecSpot;
  //    public final static BinanceTradeRestSpec tradeRestSpecUsd;
  public final static BinanceTradeRestSpec tradeRestSpecTestSpot;
  public final static BinanceTradeRestSpec tradeRestSpecTestUsd;

  public final static BinanceMarketRestSpec marketRestSpecSpot;
  public final static BinanceMarketRestSpec marketRestSpecTestSpot;
  //    public final static BinanceMarketRestSpec marketRestSpecUsd;
  public final static BinanceMarketRestSpec marketRestSpecTestUsd;

  static {
    signatureSpotProducer = new BinanceSignatureProducer(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
//        signatureUsdProducer = new BinanceSignatureProducer(PrivateConfig.SPOT_API_KEY, PrivateConfig.SPOT_SECRET_KEY);
    signatureSpotProducerTest = new BinanceSignatureProducer(PrivateConfig.TESTNET_SPOT_API_KEY,
      PrivateConfig.TESTNET_SPOT_SECRET_KEY);
    signatureUsdProducerTest = new BinanceSignatureProducer(PrivateConfig.TESTNET_USD_API_KEY,
      PrivateConfig.TESTNET_USD_SECRET_KEY);

    webClientSpot = WebClient.create(PrivateConfig.SPOT_BASE_URL);
//        webClientUsd = WebClient.create(PrivateConfig.SPOT_BASE_URL);
    webClientTestSpot = WebClient.create(PrivateConfig.TESTNET_SPOT_URL);
    webClientTestUsd = WebClient.create(PrivateConfig.TESTNET_USD_URL);

    tradeRestSpecSpot = new BinanceTradeRestSpecImpl(webClientSpot, signatureSpotProducer);
//        tradeRestSpecUsd = new BinanceTradeRestSpecImpl(webClientTest, signatureProducerTest);
    tradeRestSpecTestSpot = new BinanceTradeRestSpecImpl(webClientTestSpot, signatureSpotProducerTest);
    tradeRestSpecTestUsd = new BinanceTradeRestSpecImpl(webClientTestUsd, signatureUsdProducerTest);

    marketRestSpecSpot = new BinanceMarketRestSpecImpl(webClientSpot, signatureSpotProducer);
    marketRestSpecTestSpot = new BinanceMarketRestSpecImpl(webClientTestSpot, signatureSpotProducerTest);

//        marketRestSpecSpot = new BinanceMarketRestSpecImpl(webClientUsd, signatureUsdProducer);
    marketRestSpecTestUsd = new BinanceMarketRestSpecImpl(webClientTestUsd, signatureUsdProducerTest);
  }
}
