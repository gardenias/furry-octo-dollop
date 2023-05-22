package com.g.fod.apps.main.bench.core;

import lombok.Data;

import com.p.common.base.domain.UserId;

@Data
public class User {

  private UserId uid;
  private String apiKey;

  public void setUid(long uid) {
    this.uid = UserId.of(uid);
  }
}
