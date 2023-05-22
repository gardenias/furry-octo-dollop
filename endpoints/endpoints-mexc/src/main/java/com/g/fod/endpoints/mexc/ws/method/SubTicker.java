package com.g.fod.endpoints.mexc.ws.method;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.g.fod.endpoints.mexc.ws.WSMethod;

@JsonTypeName("sub.ticker")
public class SubTicker extends WSMethod {

  private Param param;

  public SubTicker(String symbol) {
    this.param = new Param();
    param.setSymbol(symbol);
  }

  @Setter
  @Getter
  public static class Param implements Serializable {

    private String symbol;
  }
}
