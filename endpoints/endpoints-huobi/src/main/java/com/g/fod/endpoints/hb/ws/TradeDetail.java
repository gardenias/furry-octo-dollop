package com.g.fod.endpoints.hb.ws;

import java.util.List;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.p.common.base.json.JsonObjectMapperConstant;

@Getter
@ToString
public class TradeDetail {

  private String symbol;
  private List<Trade> data;

  @JsonProperty("ch")
  public void setCh(String ch) {
    this.symbol = ch.substring(7, ch.indexOf('.', 7));
  }

  @SneakyThrows
  @JsonProperty("tick")
  public void setTick(JsonNode node) {
    String dataStr = node.get("data").toString();
    ObjectMapper m = JsonObjectMapperConstant.M;
    TypeFactory typeFactory = m.getTypeFactory();
    this.data = m.readValue(dataStr,
      typeFactory.constructCollectionType(List.class, Trade.class));
  }

  public int size() {
    return data == null ? 0 : data.size();
  }
}
