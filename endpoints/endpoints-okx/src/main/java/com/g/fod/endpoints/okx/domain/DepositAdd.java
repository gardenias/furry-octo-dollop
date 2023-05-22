package com.g.fod.endpoints.okx.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepositAdd implements Serializable {

  // 	充值地址
  private String addr;
  // 	部分币种充值需要标签，若不需要则不返回此字段
  private String tag;
  // 	部分币种充值需要标签，若不需要则不返回此字段
  private String memo;
  // 	部分币种充值需要此字段（payment_id），若不需要则不返回此字段
  private String pmtId;
  // 	充值地址备注，部分币种充值需要，若不需要则不返回此字段 如币种TONCOIN的充值地址备注标签名为comment,则该字段返回：{'comment':'123456'}
  private Object addrEx;
  // 	币种，如BTC
  private String ccy;
  // 	币种链信息 有的币种下有多个链，必须要做区分，如USDT下有USDT-ERC20，USDT-TRC20，USDT-Omni多个链
  private String chain;
  // 	转入账户 6：资金账户 18：交易账户
  private String to;
  // 	该地址是否为页面选中的地址
  private Boolean selected;
  // 	合约地址后6位
  private String ctAddr;

}
