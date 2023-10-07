package com.g.fod.endpoints.okx.ws.args;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.g.fod.openapi.spec.domain.IEnum;

public interface ChannelType extends IEnum<String> {

  default SubArg arg() {
    return new SubArg().setChannel(this);
  }

  enum Types implements ChannelType {
    INSTRUMENTS("instruments"),
    TICKERS("tickers"),
    TRADES("trades"),
    FUNDING_RATE("funding-rate"),
    STATUS("status"),
    TYPE("type"),

    ACCOUNT("account"),
    POSITIONS("positions"),
    ORDERS("orders"),
    ORDERS_ALGO("orders-algo"),
    ALGO_ADVANCE("algo-advance"),
    LIQUIDATION_WARNING("liquidation-warning"),
    BALANCE_AND_POSITION("balance_and_position"),

    ;

    @Getter
    private final String code;

    Types(String code) {
      this.code = code;
    }

    private static final Map<String, Types> INDEX = new HashMap<>();

    static {
      final Map<String, Types> tmp = new HashMap<>();
      for (Types type : Types.values()) {
        tmp.put(type.getCode(), type);
      }
    }

    @JsonCreator
    public static Types of(String code) {
      return INDEX.get(code);
    }
  }
}
