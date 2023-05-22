package com.g.fod.endpoints.hb.ws;

import lombok.Data;

import com.p.common.base.domain.Price;
import com.p.common.base.domain.Quantity;
import com.p.common.base.domain.Side;
import com.p.common.base.domain.TradeId;
import com.p.common.base.time.EpochMillis;

@Data
public class Trade {

  private TradeId tradeId;
  private Side side;
  private Price price;
  private Quantity amount;
  private EpochMillis ts;

  public void setDirection(String direction) {
    if ("buy".equals(direction)) {this.side = Side.BUY;} else {this.side = Side.SELL;}
  }

}
