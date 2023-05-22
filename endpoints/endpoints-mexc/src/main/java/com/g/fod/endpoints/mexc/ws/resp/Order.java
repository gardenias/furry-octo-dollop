package com.g.fod.endpoints.mexc.ws.resp;

import com.g.fod.endpoints.mexc.domain.Dict.OpenType;
import com.g.fod.endpoints.mexc.domain.Dict.OrderSide;
import com.g.fod.endpoints.mexc.domain.Dict.OrderType;
import com.g.fod.endpoints.mexc.ws.WsResp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.p.common.base.domain.Amount;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.Price;
import com.p.common.base.domain.Vol;
import com.p.common.base.time.EpochMillis;

@Getter
@Setter
@Accessors(chain = true)
public class Order extends WsResp {

  @Delegate
  private Data data;

  @Setter
  @Getter
  public static class Data {

    private OrderId orderId;
    private String symbol;
    private String externalOid;
    private Long positionId;
    private OpenType openType;
    private Price price;
    private Vol vol;
    private int leverage;
    private OrderSide side;
    private int category;
    private OrderType orderType;
    private EpochMillis createTime;

    private Amount orderMargin;
    private Amount usedMargin;

    private Price dealAvgPrice;
    private Vol dealVol;

    private int errorCode;

    private String feeCurrency;
    private Amount makerFee;
    private Amount takerFee;

    private Vol remainVol;
    private Amount profit;

    private int state;

    private EpochMillis updateTime;

    private long version;
  }

}
