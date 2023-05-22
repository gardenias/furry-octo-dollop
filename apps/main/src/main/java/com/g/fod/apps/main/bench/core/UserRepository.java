package com.g.fod.apps.main.bench.core;

import java.util.Map;

import com.g.fod.endpoints.x.ws.OrderResp;
import com.p.common.base.domain.InstrumentId;
import com.p.common.base.domain.OrderId;

public interface UserRepository {

  Map<OrderId, InstrumentId> order(OrderResp orderResp);

  void cancel(OrderId orderId, long nanoTime);

  void cancelMills(OrderId orderId, long nanoTime);

  void remove(OrderId orderId);
}
