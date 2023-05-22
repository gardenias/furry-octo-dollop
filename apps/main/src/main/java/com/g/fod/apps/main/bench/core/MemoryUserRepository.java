package com.g.fod.apps.main.bench.core;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.g.fod.endpoints.x.ws.OrderResp;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

import com.p.common.base.domain.InstrumentId;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.OrderState;
import com.p.common.base.domain.Role;
import com.timgroup.statsd.StatsDClient;

@Slf4j
public class MemoryUserRepository implements UserRepository {

  public static final long NANOS = TimeUnit.MILLISECONDS.toNanos(100);
  private final ConcurrentHashMap<OrderId, OrderResp> orders = new ConcurrentHashMap<>();
  private final Map<OrderId, Long> cancelEpochNano = new ConcurrentHashMap<>();
  private final Map<OrderId, Long> cancelEpochMills = new ConcurrentHashMap<>();
  private final StatsDClient dClient;
  private AtomicLong th = new AtomicLong(System.currentTimeMillis());

  public MemoryUserRepository(StatsDClient dClient) {this.dClient = dClient;}

  @Override
  public void remove(OrderId orderId) {
    orders.remove(orderId);
  }

  @Override
  public void cancelMills(OrderId orderId, long nanoTime) {
    cancelEpochMills.put(orderId, nanoTime);
  }

  @Override
  public void cancel(OrderId orderId, long nanoTime) {
    cancelEpochNano.put(orderId, nanoTime);
  }

  @Override
  public Map<OrderId, InstrumentId> order(OrderResp orderResp) {
    val ordId = orderResp.getOrdId();
    val ordState = orderResp.getOrdState();

    // place, cancel,
    if (orderResp.getRole() == null || orderResp.getRole() == Role.TAKER) {
      if (ordState == OrderState.CANCELED) {
        Long cancelTime = cancelEpochNano.remove(ordId);
        Long cancelTimeMill = cancelEpochMills.remove(ordId);

        if (cancelTime != null) {
          long nanoTime = System.nanoTime();
          long latency = nanoTime - cancelTime;
          if (latency > NANOS) {
            log.warn("[Slow][to_cancel]{},{},{},{}={}", ordId, cancelTime,
              nanoTime, nanoTime - cancelTime, latency);

            log.warn("[Slow][Mill][to_cancel]{},{},{},{},{}", ordId, cancelTimeMill,
              orderResp.getTimestamp(),
              orderResp.getTimestamp().getValue() - cancelTimeMill,
              System.currentTimeMillis() - cancelTimeMill);
          }
          dClient.time("round.trip.to_cancel", latency, "state:" + ordState);
        } else {log.warn("Cancel Request time not recorded!");}
      } else {
        String[] nanomills = orderResp.getClOrdId().split("nanomill");
        String clOrdId = nanomills[0];
        if (StringUtils.isNumeric(clOrdId)) {
          long nanoTime = System.nanoTime();
          Long clOrdIdTime = Long.valueOf(clOrdId);
          Long clOrdIdMillTime = Long.valueOf(nanomills[1]);
          long latency = nanoTime - clOrdIdTime;
          if (latency > NANOS) {
            log.warn("[Slow][to_live]{},{},{},{}={}", ordId, clOrdIdTime,
              nanoTime, nanoTime - clOrdIdTime, latency);

            log.warn("[Slow][Mill][to_live]{},{},{},{},{}", ordId, clOrdIdMillTime,
              orderResp.getTimestamp(),
              orderResp.getTimestamp().getValue() - clOrdIdMillTime,
              System.currentTimeMillis() - clOrdIdMillTime);
          }
          dClient.time("round.trip.to_live", latency, "state:" + ordState);
        } else {
          log.warn("round.trip.to_live.missing", orderResp.getOrdId());
        }
      }
    }

    orders.compute(ordId, (orderId, oldOrderResp) -> {
      if (oldOrderResp == null) {
        if (log.isTraceEnabled()) {
          log.trace("[order.state.transfer]:{0} -> {}, {} {} {} {}", ordState,
            orderResp.getOrdId(), orderResp.getOrdPrice(), orderResp.getOrdQty(),
            orderResp.getOrdAmt());
        }
        return orderResp;
      }
      if (log.isTraceEnabled()) {
        log.trace("[order.state.transfer]:{} -> {}, remainingQty:{}",
          oldOrderResp.getOrdState(), ordState, orderResp.getRemainingQty());
      }
      return orderResp;
    });

    if (!orderResp.getOrdState().isActive()) {
      if (log.isTraceEnabled()) {
        log.trace(" [R] {},{},{},{}",
          orderResp.getUserId(), orderResp.getInstrumentId(), orderResp.getOrdId(),
          orderResp.getOrdState());
      }

      orders.remove(ordId);
      cancelEpochNano.remove(ordId);
    }

    long pre = th.get();
    if (System.currentTimeMillis() > pre) {
      log.info("[Stats]{}:{}", orderResp.getUserId(), orders.size());
      th.compareAndExchange(pre, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30));
    }
    return orders.entrySet().stream()
      .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().getInstrumentId()));
  }
}
