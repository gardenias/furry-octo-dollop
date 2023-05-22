package com.g.fod.endpoints.binance.client;

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

public interface SpotClient {

  Blvt createBlvt();

  BSwap createBswap();

  C2C createC2C();

  Convert createConvert();

  CryptoLoans createCryptoLoans();

  Fiat createFiat();

  Futures createFutures();

  GiftCard createGiftCard();

  Market createMarket();

  Margin createMargin();

  Mining createMining();

  NFT createNFT();

  Pay createPay();

  Rebate createRebate();

  Savings createSavings();

  SubAccount createSubAccount();

  Trade createTrade();

  UserData createUserData();

  Wallet createWallet();
}
