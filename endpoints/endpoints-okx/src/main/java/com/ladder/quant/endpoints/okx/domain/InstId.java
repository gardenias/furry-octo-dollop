package com.ladder.quant.endpoints.okx.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import com.ladder.common.base.StrType;

@EqualsAndHashCode(callSuper = true)
public class InstId extends StrType {

    public InstId(@NonNull String value) {
        super(value);
    }
}