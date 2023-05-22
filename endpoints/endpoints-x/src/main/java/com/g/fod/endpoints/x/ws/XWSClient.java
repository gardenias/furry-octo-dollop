package com.g.fod.endpoints.x.ws;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

import com.g.common.endpoints.core.ws.AbstractWebSocketClient;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;

@Slf4j
public class XWSClient extends AbstractWebSocketClient {

  private final AtomicInteger seq = new AtomicInteger(1);

  public XWSClient(WebSocketHandler webSocketHandler, String uriTemplate, long timeoutEpochMills,
    Object... uriVariables) {
    super(webSocketHandler, uriTemplate, timeoutEpochMills, uriVariables);
  }

  public void login(String token, long userId, String... channelNames) throws IOException {
    operator.exec(socketSession -> socketSession.sendMessage(new TextMessage(
      "{\"op\":\"LOGIN\",\"channel\":[\"!@order\"],\"auth\":{\"token\":\"" + token
        + "\"},\"userId\": \"" + userId + "\",\"id\":" + seq.getAndIncrement() + "}")
    ));
  }

  public void login(String token, String... channelNames) throws IOException {
    login(token, 0, channelNames);
  }

  public void sub(String... channelNames) throws IOException {
    operator.exec(socketSession -> {
      for (String channelName : channelNames) {
        socketSession.sendMessage(new TextMessage(
          "{\"op\":\"SUB\","
            + "\"channel\":[\"" + channelName + "\"],"
            + "\"id\":" + seq.getAndIncrement() + ","
            + "\"cts\":" + System.currentTimeMillis() + "}"));
      }
    });
  }

  public void unsub(String... channelNames) throws IOException {
    operator.exec(socketSession -> {
      for (String channelName : channelNames) {
        socketSession.sendMessage(new TextMessage(
          "{\"op\":\"UNSUB\","
            + "\"channel\":[\"" + channelName + "\"],"
            + "\"id\":" + seq.getAndIncrement() + ","
            + "\"cts\":" + System.currentTimeMillis() + "}"));
      }
    });
  }

  public void ping() {

  }

  public void pong() throws IOException {
    operator.exec(socketSession -> socketSession.sendMessage(new PongMessage()));
  }

  public void close() {
    operator.stop();
  }
}
