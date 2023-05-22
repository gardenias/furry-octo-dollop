package com.g.fod.endpoints.mexc.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.p.common.base.domain.Amount;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.Price;
import com.p.common.base.domain.Side;
import com.p.common.base.domain.Vol;
import com.p.common.base.time.EpochMillis;

@Setter
@Getter
public class Order implements Serializable {

  private OrderId orderId;
  private String symbol;
  private Long positionId;
  private Price price;
  private Vol vol;
  private int leverage;
  private Side side;
  private int category;
  private int orderType;
  private Price dealAvgPrice;
  private Vol dealVol;
  private Amount orderMargin;
  private Amount takerFee;
  private Amount makerFee;
  private Amount profit;
  private String feeCurrency;
  private int openType;
  private int state;
  private String externalOid;
  private String errorCode;
  private Amount usedMargin;
  private EpochMillis createTime;
  private EpochMillis updateTime;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
