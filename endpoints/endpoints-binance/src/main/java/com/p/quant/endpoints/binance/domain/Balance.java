package com.p.quant.endpoints.binance.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Balance implements Serializable {

    // 		币种，如 BTC
    private String ccy;
    // 		余额
    private String bal;
    // 		冻结（不可用）
    private String frozenBal;
    // 		可用余额
    private String availBal;
}
