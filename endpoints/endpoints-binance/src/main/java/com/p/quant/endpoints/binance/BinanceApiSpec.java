package com.p.quant.endpoints.binance;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

import com.p.quant.endpoints.binance.spec.BinanceAccountRestSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
@Accessors(fluent = true)
public class BinanceApiSpec {

    @Delegate
    private final BinanceAccountRestSpec account;


    @Autowired
    public BinanceApiSpec(
        BinanceAccountRestSpec accountRestSpec) {
        this.account = accountRestSpec;

    }
}
