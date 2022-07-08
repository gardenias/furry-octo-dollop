package com.p.quant.endpoints.binance.client;

import com.p.quant.endpoints.binance.client.impl.spot.*;


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
