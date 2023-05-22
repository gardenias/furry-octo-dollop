package com.g.fod.endpoints.mexc;

import com.g.fod.endpoints.mexc.ws.method.Login;
import com.g.fod.endpoints.mexc.ws.method.SubTickers;
import com.p.common.base.json.JsonPrinter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MethodTests {

  @Test
  void methodPropertySerializeTest() {
    Login login = new Login();
    SubTickers subTickers = new SubTickers();
    String loginJson = JsonPrinter.jsonPrint(login);
    assertThat(loginJson).contains("\"method\":\"login\"");
    String subTickersJson = JsonPrinter.jsonPrint(subTickers);
    assertThat(subTickersJson).contains("\"method\":\"sub.tickers\"");
  }
}
