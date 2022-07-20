package com.ladder.quant.endpoints.okx.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Fill implements Serializable {

  // 		产品类型
  private String instType;
  // 		产品 ID
  private String instId;
  // 		最新成交 ID
  private String tradeId;
  // 		订单 ID
  private String ordId;
  // 		用户自定义订单ID
  private String clOrdId;
  // 		账单 ID
  private String billId;
  // 		订单标签
  private String tag;
  // 		最新成交价格
  private String fillPx;
  // 		最新成交数量
  private String fillSz;
  // 		订单方向 buy：买 sell：卖
  private String side;
  // 		持仓方向 long：多 short：空 单向持仓模式返回 net
  private String posSide;
  // 		流动性方向 T：taker M：maker
  private String execType;
  // 		交易手续费币种或者返佣金币种
  private String feeCcy;
  // 		手续费金额或者返佣金额 ，手续费扣除 为 ‘负数’，如 -0.01 ； 手续费返佣 为 ‘正数’，如 0.01
  private String fee;
  // 		成交明细产生时间，Unix 时间戳的毫秒数格式，如 1597026383085
  private String ts;
}
