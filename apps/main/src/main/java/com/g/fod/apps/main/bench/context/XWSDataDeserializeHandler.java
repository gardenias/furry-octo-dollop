package com.g.fod.apps.main.bench.context;

import com.g.fod.endpoints.x.ws.Resp;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmax.disruptor.EventHandler;
import com.p.common.base.json.JsonObjectMapperConstant;
import com.timgroup.statsd.StatsDClient;

@Slf4j
public class XWSDataDeserializeHandler implements EventHandler<XWSDataEvent> {

  private final ObjectMapper MAPPER = JsonObjectMapperConstant.M;
  private final StatsDClient dClient;

  public XWSDataDeserializeHandler(StatsDClient dClient) {
    this.dClient = dClient;
  }

  @Override
  public void onEvent(XWSDataEvent event, long sequence, boolean endOfBatch) throws Exception {
    try {
      Resp resp = MAPPER.readValue(event.getPayload(), Resp.class);
      if (resp == null) {return;}

      dClient.count("downstream.pri", 1, "type:" + resp.getType());
      event.setResp(resp);
      if (log.isTraceEnabled()) {
        log.trace("{\"type\":{},\"data\":{}}", resp.getType(), resp);
      }
    } catch (Exception e) {
      log.error("PAYLOAD:{} ,ERR:{}", event.getPayload(), e.getMessage());
    }
  }
}
