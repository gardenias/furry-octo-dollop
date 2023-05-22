package com.g.fod.apps.main.bench.properties;

import java.util.List;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("market.source.hb")
public class MarketSourceProperties {

  private String wsHost;
  private List<String> topic;
  private long timeoutEpochMill = 5000;

}
