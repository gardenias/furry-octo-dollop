package com.g.fod.apps.rt.consumer;

import java.util.function.Consumer;

import com.g.fod.apps.rt.Command.ReduceOnly;
import com.g.fod.apps.rt.DisruptorDsl;
import com.g.fod.endpoints.mexc.ws.resp.Position;

import com.g.fod.apps.rt.CommandWrapperEvent;

import lombok.extern.slf4j.Slf4j;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.g.fod.endpoints.mexc.ws.WsResp;
import com.p.common.base.time.EpochMillis;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PositionConsumer implements Consumer<WsResp> {

  private final DisruptorDsl disruptorDsl;

  public PositionConsumer(DisruptorDsl disruptorDsl) {
    this.disruptorDsl = disruptorDsl;
  }

  final EventTranslatorOneArg<CommandWrapperEvent, Position> reduceOnlyCommandTranslator =
    (event, sequence, arg0) -> {
      event.setCommand(
        new ReduceOnly(arg0.getPositionId(), arg0.getSymbol(), arg0.getRemainVol(), arg0.getOpenType()));
      event.setEpochMillis(new EpochMillis(System.currentTimeMillis()));
    };

  @Override
  public void accept(WsResp wsResp) {
    if (wsResp instanceof Position) {
      final Position position = (Position) wsResp;
      disruptorDsl.publishEvent(reduceOnlyCommandTranslator, position);
    }
  }
}
