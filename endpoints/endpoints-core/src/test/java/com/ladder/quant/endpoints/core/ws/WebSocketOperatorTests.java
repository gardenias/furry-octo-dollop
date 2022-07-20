package com.ladder.quant.endpoints.core.ws;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.fasterxml.jackson.databind.JsonNode;
import com.p.common.base.json.JsonPrinter;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.LoggingWebSocketHandlerDecorator;

import static com.p.common.base.io.ZipUtil.decompress;
import static com.p.common.base.json.JsonObjectMapperConstant.M;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class WebSocketOperatorTests {

  public static final String uriTemplate = "wss://api.huobi.pro/ws";

  @Test
  void hbMarketTradeDetailTest() throws Exception {
    WebSocketOperator operator = new WebSocketOperator(
      new LoggingWebSocketHandlerDecorator(new BinaryWebSocketHandler() {
        Map<String, Long> pong = new HashMap<>(1);

        @Override
        protected void handleBinaryMessage(@NonNull WebSocketSession session,
          @NonNull BinaryMessage binaryMessage)
          throws Exception {
          val message =
            new String(decompress(binaryMessage.getPayload().array()), StandardCharsets.UTF_8);
          JsonNode jsonNode = JsonPrinter.om.readTree(message);

          log.info("< {}", message);
          if (jsonNode.has("ping")) {
            long pingValue = jsonNode.get("ping").asLong();
            pong.put("pong", pingValue);
            String pongPayload = M.writeValueAsString(pong);
            session.sendMessage(new TextMessage(pongPayload));
            log.info("> {}", pongPayload);
          }
        }
      }), uriTemplate, 2000);

    operator.start().doOnSuccess(latency -> {
      assertThat(latency).isPositive();
      if (latency > 0) {
        operator.exec(session -> session.sendMessage(new TextMessage(
          "{\"sub\":\"market.btcusdt.bbo\",\"id\":\"2\"}")));
        operator.exec(session -> session.sendMessage(new TextMessage(
          "{\"sub\":\"market.btcusdt.trade.detail\",\"symbol\":\"btcusdt\"}")));
      } else {
        log.warn("Nagtive latency {}", latency);
      }
    }).block();

    val stop = System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(500);
    while (System.currentTimeMillis() < stop && operator.isConnected()) {
      TimeUnit.MILLISECONDS.sleep(100);
      log.info("connection status:{}", operator.isConnected());
    }
    operator.stop();
    log.info("connection status:{}", operator.isConnected());
  }
}
