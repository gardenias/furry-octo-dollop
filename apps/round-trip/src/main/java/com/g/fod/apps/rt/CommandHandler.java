package com.g.fod.apps.rt;

import lombok.extern.slf4j.Slf4j;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.g.fod.endpoints.mexc.MexcApiSpec;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandHandler implements EventHandler<CommandWrapperEvent> {

  private final MexcApiSpec apiSpec;
  private final Disruptor<CommandWrapperEvent> disruptor;

  public CommandHandler(MexcApiSpec apiSpec,
    Disruptor<CommandWrapperEvent> disruptor) {
    this.apiSpec = apiSpec;
    this.disruptor = disruptor;
  }

  @Override
  public void onEvent(CommandWrapperEvent event, long sequence, boolean endOfBatch) throws Exception {
    event.getCommand().exec(apiSpec).block();
  }
}
