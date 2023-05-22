package com.g.fod.endpoints.mexc;

import java.time.Duration;
import java.util.function.Consumer;

import com.g.fod.endpoints.mexc.ws.method.Login;
import com.g.fod.endpoints.mexc.ws.method.Ping;
import com.g.fod.endpoints.mexc.ws.method.SubTicker;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import com.g.common.endpoints.core.ws.AbstractWebSocketClient;
import com.g.fod.endpoints.mexc.ws.MexcWebSocketMessageHandler;
import com.g.fod.endpoints.mexc.ws.WsResp;
import com.p.common.base.SignatureUtils;
import org.slf4j.Logger;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Slf4j
public class MexcWSClient extends AbstractWebSocketClient {

  private final Disposable disposable;
  private static final Consumer<WsResp> NOOP_CONSUMER = wsResp -> {};

  public MexcWSClient(String uriTemplate, long timeoutEpochMills, Object... uriVariables) {
    super(new MexcWebSocketMessageHandler(log, NOOP_CONSUMER), uriTemplate, timeoutEpochMills, uriVariables);
    disposable = Flux.interval(Duration.ofSeconds(1)).subscribe(aLong -> ping());
  }

  public MexcWSClient(Consumer<WsResp> consumer, Logger log,
    String uriTemplate, long timeoutEpochMills, Object... uriVariables) {
    super(new MexcWebSocketMessageHandler(log, consumer), uriTemplate, timeoutEpochMills, uriVariables);
    disposable = Flux.interval(Duration.ofSeconds(1)).subscribe(aLong -> ping());
  }

  @SneakyThrows
  public void login(String key, String sec) {
    SignatureUtils.SignVo signVo = new SignatureUtils.SignVo();
    signVo.setAccessKey(key);
    signVo.setSecretKey(sec);
    String signature = SignatureUtils.signature(signVo);

    Login login = new Login();
    login.setApiKey(key).setSignature(signature).setReqTime(signVo.getReqTime());

    operator.exec(session -> send(session, login));
  }

  @SneakyThrows
  public void ticker(String symbol) {
    operator.exec(session -> send(session, new SubTicker(symbol)));
  }

  @SneakyThrows
  public void ping() {
    operator.exec(session -> send(session, Ping.PING));
  }

  public void close() {
    operator.stop();
    disposable.dispose();
  }

}
