package com.ladder.quant.apps.gateway.domain;

import com.ladder.quant.endpoints.core.domain.IEnum;

import lombok.Getter;

public final class Dict {

    enum Platform implements IEnum<String> {

        OKX,
        BINANCE,
        ;

        @Override
        public String getCode() {
            return name();
        }
    }


    enum Direction implements IEnum<String> {

        LINEAR("linear"),
        INVERSE("inverse"),
        ;

        @Getter
        private final String code;

        Direction(String code) {
            this.code = code;
        }
    }

}
