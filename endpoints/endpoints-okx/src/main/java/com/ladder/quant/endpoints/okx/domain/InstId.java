package com.ladder.quant.endpoints.okx.domain;

import com.p.common.base.StrType;

import lombok.EqualsAndHashCode;
import lombok.NonNull;


@EqualsAndHashCode(callSuper = true)
public class InstId extends StrType {

    public InstId(@NonNull String value) {
        super(value);
    }
}
