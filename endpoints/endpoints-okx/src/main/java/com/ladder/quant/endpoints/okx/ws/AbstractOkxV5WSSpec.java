package com.ladder.quant.endpoints.okx.ws;

import java.time.Duration;

import com.ladder.quant.endpoints.core.ws.AbstractWebSocketClient;

import com.ladder.quant.endpoints.okx.ws.args.Arg;
import org.springframework.web.socket.WebSocketHandler;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AbstractOkxV5WSSpec implements AutoCloseable {

    protected final Disposable disposable;

    protected final AbstractWebSocketClient client;

    public AbstractOkxV5WSSpec(WebSocketHandler handler, String uriTemplate,
        long timeoutEpochMills, Object... uriVariables) {
        this.client = new AbstractWebSocketClient(handler, uriTemplate, timeoutEpochMills, uriVariables);
        disposable = Flux.interval(Duration.ofSeconds(10)).subscribe(aLong -> ping());
    }

    protected void subscribe(Arg... args) {
        exec(OpType.SUB.args(args));
    }

    protected void exec(Op op) {
        client.getOperator().exec(session -> client.send(session, op));
    }

    public void ping() {
//        client.getOperator().exec(session -> client.send(session, Ping.PING));
    }

    public void close() {
        client.getOperator().stop();
        disposable.dispose();
    }

    public Mono<Long> start() {
        return client.start();
    }
}
