package com.ladder.quant.endpoints.core.rest;

import com.p.common.base.time.EpochNano;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
public abstract class Req {

    @JsonIgnore
    private EpochNano epochNano = EpochNano.of(System.nanoTime());

    @JsonIgnore
    public String getSignatureContent() {return "";}

}
