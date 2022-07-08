package com.ladder.quant.endpoints.okx;

import com.g.common.endpoints.core.ws.LoggingWebSocketHandler;

import com.ladder.quant.endpoints.okx.rest.OkxV5AccountRestSpecImpl;

import com.ladder.quant.endpoints.okx.rest.account.AccountBalanceEndpoint;

import com.ladder.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceReq;

import com.ladder.quant.endpoints.okx.rest.account.AccountBalanceEndpoint.AccountBalanceResp;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class OkxV5WSSpecTest {

    @Test
    void priv() {
        String key = "7dc6fab8-e3d0-4a95-a740-17e74f0282c4";
        String passphrase = "OKXLr001!";
        String sec = "5514A17E24308A0CD8D866D35DC2C389";
        OkxV5AccountRestSpecImpl okxV5AccountRestSpec = new OkxV5AccountRestSpecImpl(WebClient.create("https://www.okx.com/"),
            new OkxV5SignatureHeadersProducer(key, sec));
        AccountBalanceReq req = new AccountBalanceReq();
        req.setCcy("BTC");
        Mono<AccountBalanceResp> accountBalanceRespMono = okxV5AccountRestSpec.accountBalance(req);
        accountBalanceRespMono.subscribe(a -> System.out.println("a"+a.getMessage()));
        System.out.println(accountBalanceRespMono.block());
        Mono<String> mono = Mono.justOrEmpty("test");
        mono.subscribe(a -> System.out.println(a));
    }
    @Test
    void priv1() {
        String key = "7dc6fab8-e3d0-4a95-a740-17e74f0282c4";
        String passphrase = "OKXLr001!";
        String sec = "5514A17E24308A0CD8D866D35DC2C389";
        OkxV5AccountRestSpecImpl okxV5AccountRestSpec = new OkxV5AccountRestSpecImpl(WebClient.create("https://testnet.binance.vision"),
            new OkxV5SignatureHeadersProducer(key, sec));
        AccountBalanceReq req = new AccountBalanceReq();
        req.setCcy("BTC");
        Mono<AccountBalanceResp> accountBalanceRespMono = okxV5AccountRestSpec.accountBalance(req);
        accountBalanceRespMono.subscribe(a -> System.out.println("a"+a.getMessage()));
        System.out.println(accountBalanceRespMono.block());
        Mono<String> mono = Mono.justOrEmpty("test");
        mono.subscribe(a -> System.out.println(a));
    }
}
