package com.g.fod.endpoints.okx.ws;

import com.g.fod.endpoints.okx.ws.args.Arg;
import lombok.Getter;
import lombok.val;
import com.google.common.collect.Lists;

import com.ladder.quant.endpoints.core.domain.IEnum;

enum OpType implements IEnum<String> {
  SUB("subscribe"),
  UNSUB("unsubscribe"),

  LOGIN("login"),
  ORDER("order"),
  B_ORDERS("batch-orders"),

  CANCEL_ORDER("cancel-order"),
  B_CANCEL_ORDERS("batch-cancel-orders"),

  AMEND_ORDER("amend-order"),
  B_AMEND_ORDERS("batch-amend-orders"),

  ;

  @Getter
  private final String code;

  OpType(String code) {
    this.code = code;
  }

  public Op args(Arg... args) {
    val op = new Op().setOp(this);

    if (args != null && args.length > 0) {
      op.setArgs(Lists.newArrayList(args));
    }
    return op;
  }
}
