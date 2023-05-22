package com.g.fod.endpoints.mexc.ws.method;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.g.fod.endpoints.mexc.ws.WSMethod;

@JsonTypeName("ping")
public class Ping extends WSMethod {

  public static final Ping PING = new Ping();
}
