package com.g.fod.endpoints.mexc.ws.resp;

import com.g.fod.endpoints.mexc.ws.WsResp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.p.common.base.domain.Rate;
import com.p.common.base.domain.Vol;

@Setter
@Getter
@Accessors(chain = true)
public class RiskLimitation extends WsResp {

  @Delegate
  private Order.Data data;

  @Setter
  @Getter
  public static class Data {

    private String symbol;//	合约名
    private int positionType;//	持仓类型 1:多仓，2:空仓
    private int level;//	当前风险等级
    private Vol maxVol;//	最大可持仓数量
    private int maxLeverage;//	最大杠杆倍数
    private Rate mmr;//	维持保证金率
    private Rate imr;//	初始保证金率
  }
}
