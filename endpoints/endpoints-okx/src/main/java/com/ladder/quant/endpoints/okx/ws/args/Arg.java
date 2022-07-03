package com.ladder.quant.endpoints.okx.ws.args;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class Arg {

    @JsonAnyGetter
    protected Map<String, Object> more;

    public Arg set(@NonNull String key, Object value) {
        if (value == null) {return this;}

        if (more == null) {
            more = new HashMap<>();
        }

        more.put(key, value);

        return this;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
