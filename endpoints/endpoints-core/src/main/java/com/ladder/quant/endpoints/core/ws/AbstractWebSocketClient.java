package com.ladder.quant.endpoints.core.ws;

import java.io.IOException;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import reactor.core.publisher.Mono;

@Slf4j
public class AbstractWebSocketClient implements AutoCloseable {

    protected final String uriTemplate;
    protected final long timeoutEpochMills;
    protected final Object[] uriVariables;

    @Getter
    protected final WebSocketOperator operator;
    protected final WebSocketHandler webSocketHandler;

    public AbstractWebSocketClient(WebSocketHandler webSocketHandler,
        String uriTemplate, long timeoutEpochMills, Object... uriVariables) {
        this.uriTemplate = uriTemplate;
        this.timeoutEpochMills = timeoutEpochMills;
        this.uriVariables = uriVariables;
        this.webSocketHandler = new WebSocketHandlerDecorator(webSocketHandler) {
            @Override
            public void afterConnectionEstablished(@NonNull WebSocketSession session)
                throws Exception {
                super.afterConnectionEstablished(session);
                doAfterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(@NonNull WebSocketSession session,
                @NonNull CloseStatus closeStatus)
                throws Exception {
                super.afterConnectionClosed(session, closeStatus);
                doAfterConnectionClosed(session, closeStatus);
            }
        };
        this.operator = new WebSocketOperator(
            this.webSocketHandler, this.uriTemplate, this.timeoutEpochMills, uriVariables);
    }

    public void send(WebSocketSession session, TextMessageable message) {
        try {
            if (!session.isOpen()) {
                return;
            }
            val textMessage = message.of();
            session.sendMessage(textMessage);
            if (log.isDebugEnabled()) {
                log.debug("[ws] < {}", textMessage.getPayload());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws Exception {
        this.operator.stop();
    }

    public Mono<Long> start() {
        return this.operator.start();
    }

    public void doAfterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        log.info("doAfterConnectionClosed {}", closeStatus);
    }

    public void doAfterConnectionEstablished(WebSocketSession session) {
        log.info("doAfterConnectionEstablished {}", session.getId());
    }
}
