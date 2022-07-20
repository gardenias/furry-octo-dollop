package com.ladder.quant.endpoints.core.ws;

import java.net.URI;
import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactiveJavaClientWebSocket {

  public static void main(String[] args) {

    WebSocketClient client = new ReactorNettyWebSocketClient();
    val uri = URI.create("wss://api.huobi.pro/ws");
    client.execute(uri,
        session ->
          session.send(Mono.just(session.textMessage("{\"sub\":\"market.btcusdt.bbo\",\"id\":\"2\"}")))
            .thenMany(session.receive()
              .map(WebSocketMessage::getPayloadAsText)
              .log())
            .then())
      .block(Duration.ofSeconds(1L));
  }

}
