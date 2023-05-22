package com.g.fod.apps.rt;

import lombok.experimental.Delegate;

import com.lmax.disruptor.dsl.Disruptor;

public class DisruptorDsl implements AutoCloseable {

  @Delegate
  private final Disruptor<CommandWrapperEvent> disruptor;

  public DisruptorDsl(Disruptor<CommandWrapperEvent> disruptor) {
    this.disruptor = disruptor;
  }

  @Override
  public void close() {
    disruptor.shutdown();
  }
}
