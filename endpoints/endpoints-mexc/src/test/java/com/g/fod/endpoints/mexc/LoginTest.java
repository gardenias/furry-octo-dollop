package com.g.fod.endpoints.mexc;

import com.g.fod.endpoints.mexc.ws.method.Login;
import com.p.common.base.SignatureUtils;
import com.p.common.base.json.JsonPrinter;
import org.junit.jupiter.api.Test;

class LoginTest {

  private final String sec = "4bbdb8639e244f87980ad6091d670536";
  private final String key = "mx038Uufrlv4I7KHxw";

  @Test
  void name() {
    SignatureUtils.SignVo signVo = new SignatureUtils.SignVo();
    signVo.setAccessKey(key);
    signVo.setSecretKey(sec);
    String signature = SignatureUtils.signature(signVo);

    Login login = new Login();
    login.setApiKey(key).setSignature(signature).setReqTime(signVo.getReqTime());

    System.out.println(JsonPrinter.prettyPrint(login));
  }
}
