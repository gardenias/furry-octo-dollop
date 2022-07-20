package com.ladder.quant.endpoints.okx;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.ladder.quant.endpoints.core.ws.LoggingWebSocketHandler;
import com.ladder.quant.endpoints.okx.ws.OkxV5WSPublicSpec;
import com.ladder.quant.endpoints.okx.ws.OkxV5WSUsrSpec;
import com.ladder.quant.endpoints.okx.ws.OkxV5WSUsrSpecImpl;

@Slf4j
@Getter
@Component
public class OkxV5WSSpec {

  @Accessors(fluent = true)
  private OkxV5WSUsrSpec priv;
  @Accessors(fluent = true)
  private final OkxV5WSPublicSpec pub;

  public OkxV5WSSpec(OkxV5WSUsrSpec privateSpec, OkxV5WSPublicSpec publicSpec) {
    this.priv = privateSpec;
    this.pub = publicSpec;
  }

  public void priv(String key, String sec, String passphrase) {
    if (priv == null) {
      priv = new OkxV5WSUsrSpecImpl(new LoggingWebSocketHandler(log));
    }
    priv.login(key, sec, passphrase);
  }
}
