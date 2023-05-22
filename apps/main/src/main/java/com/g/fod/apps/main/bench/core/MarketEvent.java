package com.g.fod.apps.main.bench.core;

import java.util.List;

import com.g.fod.endpoints.hb.ws.BBO;
import com.g.fod.endpoints.hb.ws.TradeDetail;

import lombok.Data;

import com.g.common.endpoints.core.rest.SymbolReq;

@Data
public class MarketEvent {

  private TradeDetail tradeDetail;
  private BBO bbo;
  private List<SymbolReq> reqs;
}
