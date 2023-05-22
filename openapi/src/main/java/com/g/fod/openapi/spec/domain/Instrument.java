package com.g.fod.openapi.spec.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.g.fod.openapi.spec.domain.Dict.Direction;
import com.g.fod.openapi.spec.domain.Dict.InstType;

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
