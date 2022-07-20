package com.ladder.quant.endpoints.core.ws;

import lombok.NonNull;

import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class DisruptorWebSocketHandler extends TextWebSocketHandler {

  private final Disruptor<MessageWrapper<String>> disruptor;

  public DisruptorWebSocketHandler(Disruptor<MessageWrapper<String>> disruptor) {
    this.disruptor = disruptor;
  }

  @Override
  public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
    disruptor.publishEvent((event, sequence, arg0) -> event.setMessage(arg0), message);
  }
}
