package com.g.fod.endpoints.binance.test.usd.model.enums;

public enum QuerySort {

  ASC("asc"),
  DESC("desc");

  private final String code;

  QuerySort(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
