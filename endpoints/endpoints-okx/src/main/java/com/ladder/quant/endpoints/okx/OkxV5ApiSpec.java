package com.ladder.quant.endpoints.okx;

import com.ladder.quant.endpoints.okx.rest.OkxV5MarketRestSpec;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

import com.ladder.quant.endpoints.okx.rest.OkxV5AccountRestSpec;
import com.ladder.quant.endpoints.okx.rest.OkxV5AssetRestSpec;
import com.ladder.quant.endpoints.okx.rest.OkxV5PubRestSpec;
import com.ladder.quant.endpoints.okx.rest.OkxV5TradeRestSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@Accessors(fluent = true)
public class OkxV5ApiSpec {

    @Delegate
    private final OkxV5AccountRestSpec account;
    @Delegate
    private final OkxV5AssetRestSpec asset;
    @Delegate
    private final OkxV5MarketRestSpec market;
    @Delegate
    private final OkxV5PubRestSpec pub;
    @Delegate
    private final OkxV5TradeRestSpec trade;

    @Autowired
    public OkxV5ApiSpec(
        OkxV5AccountRestSpec accountRestSpec,
        OkxV5AssetRestSpec assetRestSpec,
        OkxV5MarketRestSpec marketRestSpec,
        OkxV5PubRestSpec pubRestSpec,
        OkxV5TradeRestSpec tradeRestSpec) {
        this.account = accountRestSpec;
        this.asset = assetRestSpec;
        this.market = marketRestSpec;
        this.pub = pubRestSpec;
        this.trade = tradeRestSpec;
    }
}
