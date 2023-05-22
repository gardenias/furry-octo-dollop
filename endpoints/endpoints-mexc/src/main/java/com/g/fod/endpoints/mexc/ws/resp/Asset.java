package com.g.fod.endpoints.mexc.ws.resp;

import com.g.fod.endpoints.mexc.ws.WsResp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.p.common.base.domain.Amount;

@Setter
@Getter
@Accessors(chain = true)
public class Asset extends WsResp {

  @Delegate
  private Order.Data data;

  @Setter
  @Getter
  public static class Data {

    private Amount availableBalance;
    private Amount bonus;
    private String currency;
    private Amount frozenBalance;
    private Amount positionMargin;
  }
}
