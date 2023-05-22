package com.g.fod.endpoints.x.ws;

import lombok.Data;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@ToString(callSuper = true)
public class EchoResp extends Resp {

  private String sid;
  @JsonProperty("C")
  private int code;
  @JsonProperty("M")
  private String message;
  private Object echo;
}
