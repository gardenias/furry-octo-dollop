package com.g.fod.apps.main.bench.context;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.g.fod.endpoints.x.ws.Resp;

import lombok.Data;

import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.UserId;

@Data
public class XWSDataEvent implements Serializable {

  private static final long serialVersionUID = 2493172521447478762L;

  private String payload;
  private Resp resp;
  private Map<UserId, OrderId[]> orders = new ConcurrentHashMap<>();
}
