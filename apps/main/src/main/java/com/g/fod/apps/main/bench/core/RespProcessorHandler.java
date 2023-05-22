package com.g.fod.apps.main.bench.core;

import com.g.fod.apps.main.endpoints.rest.RespProcessor;
import com.g.fod.endpoints.x.ws.Resp;
import com.lmax.disruptor.EventHandler;
import com.g.fod.apps.main.bench.context.XWSDataEvent;

public class RespProcessorHandler implements EventHandler<XWSDataEvent> {

  private final RespProcessor processor;

  public RespProcessorHandler(RespProcessor processor) {
    this.processor = processor;
  }

  @Override
  public void onEvent(XWSDataEvent event, long sequence, boolean endOfBatch) throws Exception {
    Resp resp = event.getResp();

    if (resp != null) {
      processor.process(resp, event);
    }
    event.setResp(null);
  }
}
