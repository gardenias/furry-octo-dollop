package com.g.fod.endpoints.okx.ws.args;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.g.fod.endpoints.okx.domain.InstId;

@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class InstIdArg extends Arg {

  protected InstId instId;
}
