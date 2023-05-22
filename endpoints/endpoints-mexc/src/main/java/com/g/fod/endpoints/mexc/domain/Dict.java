package com.g.fod.endpoints.mexc.domain;

import lombok.Getter;

public interface Dict {

  enum OrderSide implements IEnum {
    OPEN_LONG(1), CLOSE_LONG(4), OPEN_SHORT(3), CLOSE_SHORT(2),
    ;

    @Getter
    private final int code;

    OrderSide(int code) {
      this.code = code;
    }
  }

  enum OrderType implements IEnum {
    LIMIT(1), POST_ONLY(2), IOC(3), FOK(4), MARKET(5),
    /**
     * <pre>
     * 市价转限价（MTL）定单按市价单提交并以当前最佳市场价格执行。 如果定单仅被部分执行，
     * 则余下的定单被取消并以一个具有与被执行部分同样价格的限价价格的限价单被重新提交。
     * </pre>
     */
    MTL(6),
    ;
    @Getter
    private final int code;

    OrderType(int code) {
      this.code = code;
    }
  }

  enum OpenType implements IEnum {
    CROSS(2), ISOLATE(1),
    ;
    @Getter
    private final int code;

    OpenType(int code) {
      this.code = code;
    }
  }
}
