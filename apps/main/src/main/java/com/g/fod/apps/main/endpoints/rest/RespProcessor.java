package com.g.fod.apps.main.endpoints.rest;

import com.g.fod.apps.main.bench.context.XWSDataEvent;
import com.g.fod.endpoints.x.ws.Resp;

public interface RespProcessor {

  void process(Resp resp, XWSDataEvent event);

}
