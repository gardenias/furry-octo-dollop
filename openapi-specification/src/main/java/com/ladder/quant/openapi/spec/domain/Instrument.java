package com.ladder.quant.openapi.spec.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.ladder.quant.endpoints.okx.domain.Dict.InstType;
import com.ladder.quant.openapi.spec.domain.Dict.Direction;

@Setter
@Getter
@EqualsAndHashCode
public class Instrument implements Serializable {

  private InstType instType = InstType.SWAP;

  private String id;
  private Direction direction;

  private String baseCurrency;
  private String settleCurrency;
  private String quoteCurrency;

  private String tickSize;
  private String lotSize;
  private String minSize;

  private String value;
  private String valueCurrency;

  private String mult;

  private String leverage;

  private String maxLmtSize;
  private String maxMktSize;
  private String maxStopSize;
  private String maxTriggerSize;
}
