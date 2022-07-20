package com.ladder.quant.endpoints.core.rest;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.p.common.base.time.EpochNano;

@Getter
public abstract class Req {

  @JsonIgnore
  private EpochNano epochNano = EpochNano.of(System.nanoTime());

  @JsonIgnore
  public String getSignatureContent() {return "";}

}
