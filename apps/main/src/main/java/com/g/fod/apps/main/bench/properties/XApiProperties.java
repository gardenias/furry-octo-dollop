package com.g.fod.apps.main.bench.properties;

import java.util.List;

import lombok.Data;

import com.g.fod.apps.main.bench.core.User;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "x.api")
public class XApiProperties {

  // WebClientFactory.create("http://test-1.wbfutures.cc",
  // WebClientFactory.create("http://10.228.3.93:8080",

  private String baseUri;
  private String wsUri;
  private long timeoutEpochMill = 5000;
  private List<User> users;
}
