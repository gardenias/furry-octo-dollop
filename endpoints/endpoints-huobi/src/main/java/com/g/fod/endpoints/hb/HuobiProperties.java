package com.g.fod.endpoints.hb;

import lombok.Data;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConditionalOnProperty(prefix = "endpoints.huobi", name = "enabled", havingValue = "true")
@ConfigurationProperties(prefix = "endpoints.huobi")
public class HuobiProperties {

  private String uriTemplate;

}
