package com.ladder.quant.endpoints.core.ws;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.LoggingWebSocketHandlerDecorator;
import reactor.core.publisher.Mono;

@Slf4j
public class WebSocketOperator {

    private final long timeoutEpochMills;
    private WebSocketSession webSocketSession;
    private final WebSocketConnectionManager connectionManager;

    public WebSocketOperator(WebSocketHandler webSocketHandler, String uriTemplate,
        long timeoutEpochMills,
        Object... uriVariables) {
        this.timeoutEpochMills = timeoutEpochMills;

        this.connectionManager = new WebSocketConnectionManager(
            new StandardWebSocketClient(),
            new LoggingWebSocketHandlerDecorator(webSocketHandler) {
                @Override
                public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
                    super.afterConnectionEstablished(session);
                    webSocketSession = session;
                }
            }, uriTemplate, uriVariables);
    }

    public boolean isConnected() {
        return this.webSocketSession != null && this.webSocketSession.isOpen();
    }

    public void stop() {
        log.debug("stop websocket operator");
        connectionManager.stop();
    }

    public synchronized void exec(Operation operation) {
        try {
            operation.attachTo(this.webSocketSession);
        } catch (IOException e) {
            //TODO
            throw new RuntimeException(e);
        }
    }

    public Mono<Long> start() {
        val timeoutMills = System.currentTimeMillis() + this.timeoutEpochMills;
        val start = System.currentTimeMillis();

        connectionManager.start();

        return Mono.fromCallable(() -> {
            long timeout = 0;
            while (!isConnected()
                && ((timeout = System.currentTimeMillis() - timeoutMills) < 1)) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            if (isConnected()) {
                return System.currentTimeMillis() - start;
            }
            return -timeout;
        });
    }
}
