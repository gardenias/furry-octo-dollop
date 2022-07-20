package com.ladder.quant.endpoints.core.ws;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.socket.TextMessage;

public interface TextMessageable {

  @JsonIgnore
  TextMessage of();
}
