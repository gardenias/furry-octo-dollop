package com.g.fod.apps.main.bench;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.g.fod.apps.main.bench.core.User;
import com.p.common.base.json.JsonObjectMapperConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class UserTests {

  @Test
  void initTests() throws JsonProcessingException {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(PropertyPlaceholderAutoConfiguration.class,
      UsersPropertyAutoConfiguration.class);
    TestPropertyValues
      .of(
        "user.json.str=[{\"uid\":100000000001,\"apiKey\":\"100000000001\"},{\"uid\":100000000002,\"apiKey\":\"100000000002\"},{\"uid\":100000000003,\"apiKey\":\"100000000003\"}]")
      .applyTo(context);

    context.refresh();

    UsersPropertyAutoConfiguration usersPropertyAutoConfiguration =
      context.getBean(UsersPropertyAutoConfiguration.class);

    assertThat(usersPropertyAutoConfiguration.getUsers()).hasSize(3);
  }

  @Configuration
  static class UsersPropertyAutoConfiguration {

    @Value("${user.json.str}")
    public String usersJson;

    User[] getUsers() throws JsonProcessingException {
      return JsonObjectMapperConstant.M.readValue(usersJson, User[].class);
    }
  }
}
