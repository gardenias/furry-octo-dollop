package com.ladder.quant.endpoints.core.ws;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
class ReactorWebSocketClientTest {

    @Test
    void name() throws InterruptedException {
        Flux<String> ws = Flux.fromIterable(Lists.newArrayList("A", "B", "C", "D"));
        ws.doOnNext(s -> log.info("{}", s.toLowerCase())).subscribe(s -> log.info("{}", s));

        TimeUnit.SECONDS.sleep(10);

        ws.concatWithValues("F");

        TimeUnit.SECONDS.sleep(10);

    }
}
