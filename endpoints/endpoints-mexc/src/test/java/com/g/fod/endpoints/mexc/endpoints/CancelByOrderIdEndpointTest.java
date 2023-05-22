package com.g.fod.endpoints.mexc.endpoints;

import com.p.common.base.domain.OrderId;
import com.p.common.base.json.JsonPrinter;
import org.junit.jupiter.api.Test;

class CancelByOrderIdEndpointTest {

  @Test
  void name() {
    CancelByOrderIdEndpoint.CancelReq req = new CancelByOrderIdEndpoint.CancelReq().ordId(new OrderId(1L), new OrderId(2L));
    System.out.println(JsonPrinter.prettyPrint(req));
    System.out.println(req);
  }
}
