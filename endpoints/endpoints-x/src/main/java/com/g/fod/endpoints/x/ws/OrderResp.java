package com.g.fod.endpoints.x.ws;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.p.common.base.domain.AccountId;
import com.p.common.base.domain.Amount;
import com.p.common.base.domain.CurrencyId;
import com.p.common.base.domain.InstrumentId;
import com.p.common.base.domain.MatchId;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.OrderState;
import com.p.common.base.domain.Price;
import com.p.common.base.domain.Quantity;
import com.p.common.base.domain.Rate;
import com.p.common.base.domain.Role;
import com.p.common.base.domain.Side;
import com.p.common.base.domain.TradeId;
import com.p.common.base.domain.UserId;
import com.p.common.base.domain.Version;
import com.p.common.base.time.EpochMillis;

@Data
public class OrderResp extends ChannelResp implements UserAble {

  @JsonProperty("userId")
  public UserId userId;
  @JsonProperty(value = "version")
  public Version version;
  @JsonProperty(value = "accountId")
  public AccountId accountId;
  @JsonProperty(value = "ordId")
  public OrderId ordId;
  @JsonProperty(value = "clOrdId")
  public String clOrdId;
  @JsonProperty("symbol")
  public InstrumentId instrumentId;
  @JsonProperty(value = "side")
  public Side side;
  @JsonProperty(value = "ordType")
  public String ordType;
  @JsonProperty(value = "timeInForce")
  public String timeInForce;
  @JsonProperty(value = "ordPrice")
  public Price ordPrice;
  @JsonProperty(value = "ordQty")
  public Quantity ordQty;
  @JsonProperty(value = "ordAmt")
  public Amount ordAmt;
  @JsonProperty(value = "ordState")
  public OrderState ordState;
  @JsonProperty(value = "execQty")
  public Quantity execQty;
  @JsonProperty(value = "execAmt")
  public Amount execAmt;
  @JsonProperty(value = "remainingQty")
  public Quantity remainingQty;
  @JsonProperty(value = "matchId")
  public MatchId matchId;
  @JsonProperty(value = "tradeId")
  public TradeId tradeId;
  @JsonProperty(value = "role")
  public Role role;
  @JsonProperty(value = "matchQty")
  public Quantity matchQty;
  @JsonProperty(value = "matchAmt")
  public Amount matchAmt;
  @JsonProperty(value = "selfDealingQty")
  public Quantity selfDealingQty;
  @JsonProperty(value = "actualFeeRate")
  public Rate actualFeeRate;
  @JsonProperty(value = "feeCurrencyId")
  public CurrencyId feeCurrencyId;
  @JsonProperty(value = "fee")
  public Amount fee;
  @JsonProperty(value = "timestamp")
  public EpochMillis timestamp;

//    @Override
//    public Map<OrderId, InstrumentId> exec(UserRepository userRepository) {
//        return userRepository.order(this);
//    }
}
