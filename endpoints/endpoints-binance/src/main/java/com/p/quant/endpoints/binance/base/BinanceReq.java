package com.p.quant.endpoints.binance.base;

import com.g.common.endpoints.core.rest.Req;

import lombok.Data;

@Data
public abstract class BinanceReq {
    private String signature;
}
