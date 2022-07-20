package com.ladder.quant.endpoints.okx.ws.args;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PlaceOrdArg extends InstIdArg {

  // 是	交易模式保证金模式 isolated：逐仓 cross： 全仓 非保证金模式 cash：现金
  private String tdMode;
  // 否	保证金币种，仅适用于单币种保证金账户下的全仓杠杆订单
  private String ccy;
  // 否	由用户设置的订单ID字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。 '
  private String clOrdId;
  // 否	订单标签字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-16位之间。
  private String tag;
  // 是	订单方向，buy sell
  private String side;
  // 否	持仓方向在单向持仓模式下，默认 net在双向持仓模式下必填，且仅可选择 long 或 short，仅适用于交割/永续
  private String posSide;
  // 是	订单类型market：市价单 limit：限价单 post_only：只做maker单 fok：全部成交或立即取消 ioc：立即成交并取消剩余 optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续）
  private String ordType;
  // 是	当type为limit时，表示买入或卖出的数量 当type为market时，现货交易买入时，表示买入的总金额，而 当其他产品买入或卖出时，表示数量
  private String sz;
  // 否	委托价，仅适用于limit、post_only、fok、ioc类型的订单
  private String px;
  // 否	是否只减仓，true 或 false，默认false 仅适用于币币杠杆，以及买卖模式下的交割/永续 仅适用于单币种保证金模式和跨币种保证金模式
  private Boolean reduceOnly;
  // 否	市价单委托数量的类型base_ccy: 交易货币 ；quote_ccy：计价货币 仅适用于币币订单
  private String tgtCcy;
  // 否	是否禁止币币市价改单，true 或 false，默认false 为true时，余额不足时会下单失败，仅适用于币币市价单
  private Boolean banAmend;
}
