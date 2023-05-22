package com.g.fod.endpoints.okx.ws.args;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.g.fod.openapi.spec.domain.Dict.InstType;

@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SubArg extends InstIdArg {

  private ChannelType channel;
  private InstType instType;
  private String ccy;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
