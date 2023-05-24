package com.g.fod.openapi.spec.domain;

import lombok.Getter;

import com.ladder.quant.endpoints.core.domain.IEnum;

public interface Dict {

  enum Direction implements IEnum<String> {

    LINEAR("linear"),
    INVERSE("inverse"),
    ;

    @Getter
    private final String code;

    Direction(String code) {
      this.code = code;
    }
  }

  enum InstType implements IEnum<String> {
    SPOT,
    MARGIN,
    SWAP,
    FUTURES,
    OPTION,
    ANY,
    ;

    @Override
    public String getCode() {
      return name();
    }
  }
}
