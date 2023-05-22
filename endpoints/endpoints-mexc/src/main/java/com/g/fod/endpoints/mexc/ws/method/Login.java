package com.g.fod.endpoints.mexc.ws.method;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.g.fod.endpoints.mexc.ws.WSMethod;

@JsonTypeName("login")
@JsonIgnoreProperties(value = {"apiKey", "signature", "reqTime"})
@NoArgsConstructor
@Accessors(chain = true)
public class Login extends WSMethod {

  @Setter
  @Getter
  private boolean subscribe = true;
  @Getter
  @Delegate
  private final LoginParam param = new LoginParam();

  @Setter
  @Getter
  @NoArgsConstructor
  public static class LoginParam implements Serializable {

    private String apiKey;
    private String signature;
    private String reqTime;
  }
}


