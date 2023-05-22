package com.g.fod.apps.main.bench.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.g.fod.endpoints.hb.HuoBiWSClient;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.JsonNode;
import com.lmax.disruptor.dsl.Disruptor;
import com.g.fod.apps.main.bench.core.MarketEvent;
import com.g.fod.apps.main.bench.core.MarketEventProducer;
import com.g.fod.apps.main.bench.properties.MarketSourceProperties;
import com.g.fod.endpoints.hb.ws.BBO;
import com.g.fod.endpoints.hb.ws.TradeDetail;
import com.p.common.base.io.ZipUtil;
import com.timgroup.statsd.StatsDClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.LoggingWebSocketHandlerDecorator;

import static com.p.common.base.json.JsonObjectMapperConstant.M;

@Slf4j
@Component
@ConditionalOnProperty(value = "market.source.hb.enable", havingValue = "true")
public class HuobiMarketEventProducer
  extends BinaryWebSocketHandler
  implements AutoCloseable, MarketEventProducer {

  public static final String uriTemplate = "wss://api.huobi.pro/ws";

  private final Map<String, Long> pong = new HashMap<>(1);
  private final Disruptor<MarketEvent> disruptor;
  private final HuoBiWSClient hbwsClient;
  private final MarketSourceProperties properties;
  private volatile boolean running = false;
  private final StatsDClient dClient;

  public HuobiMarketEventProducer(Disruptor<MarketEvent> disruptor,
    MarketSourceProperties properties, StatsDClient dClient) {
    this.disruptor = disruptor;
    this.properties = properties;
    this.dClient = dClient;
    hbwsClient = new HuoBiWSClient(new LoggingWebSocketHandlerDecorator(this),
      uriTemplate, 100, "");
  }

  @Override
  protected void handleBinaryMessage(WebSocketSession session, BinaryMessage binaryMessage)
    throws Exception {
    String message = new String(ZipUtil.decompress(binaryMessage.getPayload().array()), "UTF-8");
    if (log.isTraceEnabled()) {
      log.trace("< {}", message);
    }
    JsonNode jsonNode = M.readTree(message);
    if (jsonNode.has("ping")) {
      long pingValue = jsonNode.get("ping").asLong();
      pong.put("pong", pingValue);
      String pongPayload = M.writeValueAsString(pong);
      session.sendMessage(new TextMessage(pongPayload));
      if (log.isTraceEnabled()) {
        log.trace("> {}", pongPayload);
      }
    } else if (jsonNode.has("ch")) {
      String ch = jsonNode.get("ch").asText();
      if (ch.endsWith(".bbo")) {
        BBO bbo = M.readValue(message, BBO.class);
        if (log.isDebugEnabled()) {
          log.debug("< {}", bbo);
        }
        disruptor.publishEvent((event, sequence, arg0) -> event.setBbo(arg0), bbo);
        dClient.count("upstream.req", 2, "type:bbo");
      } else if (ch.endsWith(".trade.detail")) {
        TradeDetail tradeDetail = M.readValue(message, TradeDetail.class);
        if (log.isDebugEnabled()) {
          log.debug("< {}", tradeDetail);
        }
        dClient.count("upstream.req", tradeDetail.size(), "type:trade");
        disruptor.publishEvent((event, sequence, arg0) -> event.setTradeDetail(arg0),
          tradeDetail);
      }
    }
  }

  @Override
  public void start() throws Exception {
    this.running = true;
    hbwsClient.start().doOnSuccess(latencyOrTimeout -> {
      if (latencyOrTimeout > 0) {
        for (String topic : properties.getTopic()) {
          try {
            hbwsClient.sub(topic);
            log.info("[SUB]{}", topic);
            TimeUnit.SECONDS.sleep(1);
          } catch (IOException e) {
            log.error(e.getMessage(), e);
          } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
            break;
          }
        }
      } else {
        log.warn("Connect to {} timeout {}:{}", latencyOrTimeout);
      }
    }).doOnError(e -> log.error(e.getMessage(), e));
  }

  @Override
  public boolean isActive() {
//    return hbwsClient.isConnected() &&
    return this.running;
  }

  @Override
  public void close() throws Exception {
    this.running = false;
    hbwsClient.close();
  }
}
