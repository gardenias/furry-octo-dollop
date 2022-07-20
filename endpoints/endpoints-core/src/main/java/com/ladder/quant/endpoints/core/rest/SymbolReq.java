package com.ladder.quant.endpoints.core.rest;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class SymbolReq extends Req {

  protected final String symbol;

  protected SymbolReq(String symbol) {
    this.symbol = symbol;
  }

}
