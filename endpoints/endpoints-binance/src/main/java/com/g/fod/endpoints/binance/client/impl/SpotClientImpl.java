package com.g.fod.endpoints.binance.client.impl;

import com.g.fod.endpoints.binance.client.enums.DefaultUrls;
import com.g.fod.endpoints.binance.client.impl.spot.BSwap;
import com.g.fod.endpoints.binance.client.impl.spot.Blvt;
import com.g.fod.endpoints.binance.client.impl.spot.C2C;
import com.g.fod.endpoints.binance.client.impl.spot.Convert;
import com.g.fod.endpoints.binance.client.impl.spot.CryptoLoans;
import com.g.fod.endpoints.binance.client.impl.spot.Fiat;
import com.g.fod.endpoints.binance.client.impl.spot.Futures;
import com.g.fod.endpoints.binance.client.impl.spot.GiftCard;
import com.g.fod.endpoints.binance.client.impl.spot.Margin;
import com.g.fod.endpoints.binance.client.impl.spot.Market;
import com.g.fod.endpoints.binance.client.impl.spot.Mining;
import com.g.fod.endpoints.binance.client.impl.spot.NFT;
import com.g.fod.endpoints.binance.client.impl.spot.Pay;
import com.g.fod.endpoints.binance.client.impl.spot.Rebate;
import com.g.fod.endpoints.binance.client.impl.spot.Savings;
import com.g.fod.endpoints.binance.client.impl.spot.SubAccount;
import com.g.fod.endpoints.binance.client.impl.spot.Trade;
import com.g.fod.endpoints.binance.client.impl.spot.UserData;
import com.g.fod.endpoints.binance.client.impl.spot.Wallet;
import com.g.fod.endpoints.binance.client.SpotClient;

public class SpotClientImpl implements SpotClient {

  private final String apiKey;
  private final String secretKey;
  private final String baseUrl;
  private boolean showLimitUsage = false;

  public SpotClientImpl() {
    this.apiKey = null;
    this.secretKey = null;
    this.baseUrl = DefaultUrls.PROD_URL;
  }

  public SpotClientImpl(String baseUrl) {
    this.apiKey = null;
    this.secretKey = null;
    this.baseUrl = baseUrl;
  }

  public SpotClientImpl(String baseUrl, boolean showLimitUsage) {
    this.apiKey = null;
    this.secretKey = null;
    this.baseUrl = baseUrl;
    this.showLimitUsage = showLimitUsage;
  }

  public SpotClientImpl(String apiKey, String secretKey) {
    this.apiKey = apiKey;
    this.secretKey = secretKey;
    this.baseUrl = DefaultUrls.PROD_URL;
  }

  public SpotClientImpl(String apiKey, String secretKey, String baseUrl) {
    this.apiKey = apiKey;
    this.secretKey = secretKey;
    this.baseUrl = baseUrl;
  }

  public void setShowLimitUsage(boolean showLimitUsage) {
    this.showLimitUsage = showLimitUsage;
  }

  @Override
  public Blvt createBlvt() {
    return new Blvt(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public BSwap createBswap() {
    return new BSwap(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public C2C createC2C() {
    return new C2C(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public Convert createConvert() {
    return new Convert(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public CryptoLoans createCryptoLoans() {return new CryptoLoans(baseUrl, apiKey, secretKey, showLimitUsage);}

  @Override
  public Fiat createFiat() {
    return new Fiat(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public Futures createFutures() {
    return new Futures(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public GiftCard createGiftCard() {return new GiftCard(baseUrl, apiKey, secretKey, showLimitUsage);}

  @Override
  public Margin createMargin() {
    return new Margin(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public Market createMarket() {
    return new Market(baseUrl, apiKey, showLimitUsage);
  }

  @Override
  public Mining createMining() {
    return new Mining(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public NFT createNFT() {
    return new NFT(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public Pay createPay() {
    return new Pay(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public Rebate createRebate() {
    return new Rebate(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public Savings createSavings() {
    return new Savings(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public SubAccount createSubAccount() {
    return new SubAccount(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public Trade createTrade() {
    return new Trade(baseUrl, apiKey, secretKey, showLimitUsage);
  }

  @Override
  public UserData createUserData() {
    return new UserData(baseUrl, apiKey, showLimitUsage);
  }

  @Override
  public Wallet createWallet() {
    return new Wallet(baseUrl, apiKey, secretKey, showLimitUsage);
  }
}
