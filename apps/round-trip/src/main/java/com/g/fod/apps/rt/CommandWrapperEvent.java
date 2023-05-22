package com.g.fod.apps.rt;

import lombok.Getter;
import lombok.Setter;

import com.p.common.base.time.EpochMillis;

@Setter
@Getter
public class CommandWrapperEvent {

  private EpochMillis epochMillis;
  private Command<?> command;
}
