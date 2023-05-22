package com.g.fod.endpoints.x.ws;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(
  use = Id.NAME,
  property = "T",
  visible = true
)
@JsonSubTypes(
  {
    @Type(value = EchoResp.class, name = "req"),
    @Type(value = EchoResp.class, name = "echo"),
    @Type(value = OrderResp.class, name = "order"),
    @Type(value = PingResp.class, name = "ping")
  }
)
@Data
public abstract class Resp {

  @JsonProperty("S")
  protected long seq;
  @JsonProperty("T")
  protected String type;
}
