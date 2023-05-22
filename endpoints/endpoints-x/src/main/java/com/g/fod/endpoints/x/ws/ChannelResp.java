package com.g.fod.endpoints.x.ws;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(
  use = Id.NAME,
  property = "channel",
  visible = true
)
@JsonSubTypes(
  {
    @Type(value = OrderResp.class, name = "order"),
  }
)
@Data
@NoArgsConstructor
public abstract class ChannelResp extends Resp {

  @JsonProperty
  @Accessors(fluent = true)
  protected String channel;

}
