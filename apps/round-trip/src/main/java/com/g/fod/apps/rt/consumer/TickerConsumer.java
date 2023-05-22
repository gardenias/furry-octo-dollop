package com.g.fod.apps.rt.consumer;

import java.util.function.Consumer;

import com.g.fod.endpoints.mexc.ws.resp.Ticker;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.g.fod.apps.rt.DisruptorDsl;
import com.g.fod.endpoints.mexc.ws.WsResp;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TickerConsumer implements Consumer<WsResp> {

  private final DisruptorDsl disruptorDsl;

  public TickerConsumer(DisruptorDsl disruptorDsl) {
    this.disruptorDsl = disruptorDsl;
  }

  @Override
  public void accept(WsResp wsResp) {
    if (!wsResp.getClass().isAssignableFrom(Ticker.class)) {return;}

    //TODO post only & take(ioc) & cancel the postOnly
    val ticker = (Ticker) wsResp;
    log.info("{}:{},{},{}", ticker.getSymbol(), ticker.getLastPrice(), ticker.getAsk1(), ticker.getBid1());
  }
}
