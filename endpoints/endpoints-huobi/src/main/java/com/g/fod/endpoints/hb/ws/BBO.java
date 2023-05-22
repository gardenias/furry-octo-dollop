package com.g.fod.endpoints.hb.ws;

import java.math.BigDecimal;

import lombok.Data;
import lombok.SneakyThrows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.p.common.base.domain.Price;
import com.p.common.base.domain.Quantity;

@Data
public class BBO {

  private String symbol;
  private long ts;
  private Price bid;
  private Quantity bidSize;
  private Price ask;
  private Quantity askSize;

  @JsonProperty("ch")
  public void setCh(String ch) {
    this.symbol = ch.substring(7, ch.indexOf('.', 7));
  }

  @SneakyThrows
  @JsonProperty("tick")
  public void setTick(JsonNode node) {

    this.ts = node.get("quoteTime").asLong();
    this.bid = Price.of(new BigDecimal(node.get("bid").asText()));
    this.bidSize = Quantity.of(new BigDecimal(node.get("bidSize").asText()));

    this.ask = Price.of(new BigDecimal(node.get("ask").asText()));
    this.askSize = Quantity.of(new BigDecimal(node.get("askSize").asText()));
  }
}
