package com.g.fod.apps.rt.consumer;

import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.g.fod.endpoints.mexc.ws.WsResp;
import com.g.fod.endpoints.mexc.ws.resp.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderConsumer implements Consumer<WsResp> {

  @Override
  public void accept(WsResp resp) {
    if (resp instanceof Order) {
      val externalOid = ((Order) resp).getExternalOid();
      val latency = System.currentTimeMillis() - Long.parseLong(externalOid);
      log.info("round.trip.ws_order = {}", latency);
    }
  }

}
