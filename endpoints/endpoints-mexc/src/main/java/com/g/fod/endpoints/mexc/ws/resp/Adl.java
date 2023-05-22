package com.g.fod.endpoints.mexc.ws.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.g.fod.endpoints.mexc.ws.WsResp;

@Setter
@Getter
@Accessors(chain = true)
public class Adl extends WsResp {

  @Delegate
  private Order.Data data;

  @Setter
  @Getter
  public static class Data {

    private Long positionId;
    private int adlLevel;
  }

}
