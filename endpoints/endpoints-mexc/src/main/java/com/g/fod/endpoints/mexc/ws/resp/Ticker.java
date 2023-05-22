package com.g.fod.endpoints.mexc.ws.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.g.fod.endpoints.mexc.ws.WsResp;
import com.p.common.base.domain.Amount;
import com.p.common.base.domain.Price;
import com.p.common.base.domain.Rate;
import com.p.common.base.domain.Vol;
import com.p.common.base.time.EpochMillis;

@Getter
@Setter
@Accessors(chain = true)
public class Ticker extends WsResp {

  private String contractId;
  private String symbol;

  private Rate fundingRate;

  private Price ask1;
  private Price lastPrice;
  private Price bid1;
  private Price indexPrice;
  private Price fairPrice;
  private Price maxBidPrice;
  private Price minAskPrice;

  private Vol holdVol;
  private Amount volume24;

  private Price high24Price;
  private Price lower24Price;
  private Rate riseFallRate;
  private Price riseFallValue;

  private EpochMillis timestamp;

}
