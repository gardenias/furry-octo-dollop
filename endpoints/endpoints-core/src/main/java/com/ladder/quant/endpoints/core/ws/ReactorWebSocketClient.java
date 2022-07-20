package com.ladder.quant.endpoints.core.ws;

import java.net.URI;
import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorWebSocketClient {

  SendListner listner;

  public void reg(SendListner listner) {
    this.listner = listner;
  }

  public static void main(String[] args) {

    val reactorWebSocketClient = new ReactorWebSocketClient();
    val client = new ReactorNettyWebSocketClient();

    client.execute(URI.create("ws://localhost:8080/event-emitter"), new ExampleHandler(reactorWebSocketClient))
      .block(Duration.ofSeconds(10L));

  }

  @Slf4j
  static class ExampleHandler implements WebSocketHandler {

    private final ReactorWebSocketClient client;

    public ExampleHandler(ReactorWebSocketClient client) {
      this.client = client;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {

      Mono<Void> input =
        session.receive().doOnNext(message -> log.info("> {}", message.getPayloadAsText())).then();

      Mono<Void> output = session.send(
        Flux.create(sink -> {
          client.reg(new SendListner() {
            @Override
            public void send(TextMessageable origin) {
              sink.next(session.textMessage(origin.of().getPayload()));
            }
          });
        }));

      return Mono.zip(input, output).then();
    }
  }
}
