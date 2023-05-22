package com.g.fod.endpoints.hb;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

import com.g.common.endpoints.core.ws.AbstractWebSocketClient;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
public class HuoBiWSClient extends AbstractWebSocketClient {

  private final AtomicInteger seq = new AtomicInteger(1);

  public HuoBiWSClient(WebSocketHandler webSocketHandler, String uriTemplate,
    long timeoutEpochMills, Object... uriVariables) {
    super(webSocketHandler, uriTemplate, timeoutEpochMills, uriVariables);
  }

  public void sub(String... chs) throws IOException {
    operator.exec(session -> {
      for (String ch : chs) {
        TextMessage message = new TextMessage(
          "{\"sub\":\"" + ch + "\",\"id\":\"" + seq.getAndIncrement() + "\"}");
        session.sendMessage(message);
        if (log.isDebugEnabled()) {
          log.debug("> {}", message.getPayload());
        }
      }
    });
  }

  @Override
  public void doAfterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
    log.warn("[HB]Closed {},{},{}",
      session.getId(), closeStatus.getCode(), closeStatus.getReason());
  }

  @Override
  public void doAfterConnectionEstablished(WebSocketSession session) {
    log.info("[HB]New {}", session.getId());
  }
}
