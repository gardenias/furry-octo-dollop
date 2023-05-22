package com.g.fod.endpoints.mexc.ws.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.g.fod.endpoints.mexc.domain.Dict.OpenType;
import com.g.fod.endpoints.mexc.ws.WsResp;
import com.p.common.base.domain.Amount;
import com.p.common.base.domain.Price;
import com.p.common.base.domain.Vol;

@Setter
@Getter
@Accessors(chain = true)
public class Position extends WsResp {

  @Delegate
  private Order.Data data;

  @Setter
  @Getter
  public static class Data {

    private String symbol;
    private Long positionId;
    private OpenType openType;
    private int positionType;
    private Price openAvgPrice;
    private int leverage;

    private Price holdAvgPrice;
    private Vol holdVol;
    private Amount holdFee;

    private Vol frozenVol;

    private boolean autoAddIm;
    private Amount oim;
    private Amount im;
    private Price liquidatePrice;

    private Vol closeVol;
    private Price closeAvgPrice;
    private Amount realised;

    private int state;
  }
}
