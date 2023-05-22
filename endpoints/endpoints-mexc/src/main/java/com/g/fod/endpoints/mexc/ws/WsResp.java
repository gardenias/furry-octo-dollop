package com.g.fod.endpoints.mexc.ws;

import java.util.function.Function;

import com.g.fod.endpoints.mexc.ws.resp.Adl;
import com.g.fod.endpoints.mexc.ws.resp.Position;
import com.g.fod.endpoints.mexc.ws.resp.Ticker;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.g.fod.endpoints.mexc.ws.resp.Asset;
import com.g.fod.endpoints.mexc.ws.resp.Order;
import com.g.fod.endpoints.mexc.ws.resp.RiskLimitation;
import com.p.common.base.time.EpochMillis;

@Setter
@Getter
@JsonTypeInfo(use = Id.NAME, property = "channel")
@JsonSubTypes({
  @Type(value = Order.class, name = "push.personal.order"),
  @Type(value = Position.class, name = "push.personal.position"),
  @Type(value = Asset.class, name = "push.personal.asset"),
  @Type(value = Adl.class, name = "push.personal.adl.level"),
  @Type(value = RiskLimitation.class, name = "push.personal.risk.limit"),
  @Type(value = Ticker.class, name = "push.ticker")
}
)
public abstract class WsResp {

  protected EpochMillis ts;

  public <R> R exec(Function<WsResp, R> function) {
    return function.apply(this);
  }

}
