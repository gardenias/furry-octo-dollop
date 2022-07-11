package com.ladder.quant.endpoints.core.rest;

import java.util.function.Consumer;

import com.p.common.base.json.JsonPrinter;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.slf4j.Logger;
import org.slf4j.helpers.NOPLogger;
import reactor.core.publisher.Mono;

@Data
public class Resp {

    private int code;
    @JsonAlias({"msg", "message"})
    private String message;

    public String getErrStr() {
        return "{\"code\":" + code + ",\"msg\":\"" + message + "\"}";
    }

    public <T> Mono<T> of(T data) {
        if (code == 0) {
            return Mono.just(data);
        }
        return Mono.empty();
    }

    public boolean success() {
        return code == 0 || code == 200;
    }

    public static class RespLoggerOnFailConsumer implements Consumer<Resp> {

        private final Req req;
        private final Logger log;

        public RespLoggerOnFailConsumer(Req req) {
            this.req = req;
            this.log = NOPLogger.NOP_LOGGER;
        }

        public RespLoggerOnFailConsumer(Req req, Logger log) {
            this.req = req;
            this.log = log;
        }

        @Override
        public void accept(Resp resp) {
            if (!resp.success() && log.isWarnEnabled()) {
                log.warn("{} < {}", resp.getErrStr(), JsonPrinter.jsonPrint(req));
            }
        }
    }
}
