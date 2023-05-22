package com.g.fod.endpoints.mexc.ws;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.g.common.endpoints.core.ws.TextMessageable;
import com.p.common.base.json.JsonPrinter;
import org.springframework.web.socket.TextMessage;

@JsonTypeInfo(use = Id.NAME, property = "method")
public abstract class WSMethod implements TextMessageable {

  @JsonIgnore
  private TextMessage delegate;

  @JsonIgnore
  public TextMessage of() {
    if (delegate == null) {delegate = new TextMessage(JsonPrinter.jsonPrint(this));}
    return delegate;
  }
}
