package com.g.fod.endpoints.mexc.ws;

import java.util.function.Consumer;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.g.common.endpoints.core.ws.LoggingWebSocketHandler;
import com.p.common.base.json.JsonPrinter;
import org.slf4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class MexcWebSocketMessageHandler extends TextWebSocketHandler {

  private final TextWebSocketHandler delegate;

  public MexcWebSocketMessageHandler(Logger log, Consumer<WsResp> consumer) {
    TextWebSocketHandler handler = new TextWebSocketHandler() {
      @Override
      protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        val wsResp = JsonPrinter.om.readValue(message.getPayload(), WsResp.class);
        consumer.accept(wsResp);
      }
    };

    if (log != null) {
      val loggingWebSocketHandler = new LoggingWebSocketHandler(log);
      delegate = new TextWebSocketHandler() {

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
          loggingWebSocketHandler.handleTextMessage(session, message);
          handler.handleMessage(session, message);

        }
      };
    } else {
      delegate = handler;
    }

  }

  @SneakyThrows
  @Override
  protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
    delegate.handleMessage(session, message);
  }

}
