package com.g.fod.apps.main.endpoints.ws;

import com.g.fod.apps.main.bench.context.XWSDataEvent;

import lombok.extern.slf4j.Slf4j;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class DisruptorWebSocketHandler extends TextWebSocketHandler {

  private static final EventTranslatorOneArg<XWSDataEvent, String> EVENT_TRANSLATOR_ONE_ARG
    = (event, sequence, arg0) -> event.setPayload(arg0);

  private final Disruptor<XWSDataEvent> disruptor;

  public DisruptorWebSocketHandler(Disruptor<XWSDataEvent> disruptor) {
    this.disruptor = disruptor;
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message)
    throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("PAYLOAD:{}", message.getPayload());
    }
    disruptor.publishEvent(EVENT_TRANSLATOR_ONE_ARG, message.getPayload());
  }
}
