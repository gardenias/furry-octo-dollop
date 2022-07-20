package com.ladder.quant.endpoints.core.ws;

import java.io.IOException;

import org.springframework.web.socket.WebSocketSession;

@FunctionalInterface
public interface Operation {

  void attachTo(WebSocketSession socketSession) throws IOException;
}
