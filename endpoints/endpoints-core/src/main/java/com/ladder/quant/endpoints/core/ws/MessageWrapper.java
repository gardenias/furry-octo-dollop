package com.ladder.quant.endpoints.core.ws;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.socket.WebSocketMessage;

@Setter
@Getter
public class MessageWrapper<T> implements Serializable {

  private WebSocketMessage<T> message;
}
