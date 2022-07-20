package com.ladder.quant.apps.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ladder.quant.endpoints.okx"})
public class Bootstrap {

  public static void main(String[] args) {
    SpringApplication.run(Bootstrap.class);
  }

}
