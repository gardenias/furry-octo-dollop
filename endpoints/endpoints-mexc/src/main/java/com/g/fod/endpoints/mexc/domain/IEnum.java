package com.g.fod.endpoints.mexc.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public interface IEnum {

  @JsonValue
  int getCode();

}
