package com.ladder.quant.endpoints.core.ws;

import lombok.NonNull;

import org.slf4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class LoggingWebSocketHandler extends TextWebSocketHandler {

  private final Logger logger;

  public LoggingWebSocketHandler(Logger logger) {
    this.logger = logger;
  }

  @Override
  public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
    if (logger.isDebugEnabled()) {
      logger.debug("> {}", message.getPayload());
    }
  }
}
