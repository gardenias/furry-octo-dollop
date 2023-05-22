package com.g.fod.endpoints.okx.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import com.p.common.base.StrType;

@EqualsAndHashCode(callSuper = true)
public class InstId extends StrType {

  public InstId(@NonNull String value) {
    super(value);
  }
}
