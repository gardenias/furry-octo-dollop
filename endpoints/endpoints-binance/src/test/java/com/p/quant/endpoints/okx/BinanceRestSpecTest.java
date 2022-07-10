package com.p.quant.endpoints.okx;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;
import com.p.quant.endpoints.binance.common.BinanceSignatureProducer;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoReq;
import com.p.quant.endpoints.binance.rest.account.spot.AccountInfoEndpoint.AccountInfoResp;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupReq;
import com.p.quant.endpoints.binance.rest.market.spot.OldTradeLookupEndpoint.OldTradeLookupResp;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListReq;
import com.p.quant.endpoints.binance.rest.market.spot.RecentTradesListEndpoint.RecentTradesListResp;
import com.p.quant.endpoints.binance.spec.BinanceAccountRestSpec;
import com.p.quant.endpoints.binance.spec.impl.BinanceAccountRestSpecImpl;
import com.p.quant.endpoints.binance.spec.impl.BinanceMarketRestSpecImpl;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class BinanceRestSpecTest {

    private static BinanceSignatureProducer signatureProducer;
    private static WebClient webClient; 
    private static BinanceAccountRestSpec accountRestSpec;
    private static BinanceMarketRestSpecImpl marketRestSpec;
    @Test
    void priv() {
    }

    
    static  {
        webClient = WebClient.create("https://testnet.binance.vision");
        signatureProducer = new BinanceSignatureProducer(
            "5c7NeG7vfMPQTUA3fxufxso4mhRHnYWhspZj4oHYiS1ktlcHzXWD5FsgJS0TvJPe",
            "HBpkbW0RhYGlFkvsgD3RUZfffC1LPgAGkpxn6l1UiQdpkl9cuyKQ0veAAGWhvVEs");
        accountRestSpec = new BinanceAccountRestSpecImpl(webClient, signatureProducer);
        marketRestSpec = new BinanceMarketRestSpecImpl(webClient, signatureProducer);
    }
    @Test
    void testAccountInfo() {
        AccountInfoReq req = new AccountInfoReq();
        req.setRecvWindow(5000L);
        req.setTimestamp(System.currentTimeMillis());
        Mono<AccountInfoResp> accountBalanceRespMono = accountRestSpec.accountInfo(req);
        System.out.println(accountBalanceRespMono.block());
    }
    @Test
    void testMarket() {
        OldTradeLookupReq req = new OldTradeLookupReq();
        req.setSymbol("BTCUSDT");
        req.setLimit(10);
        Flux<OldTradeLookupResp> oldTradeLookupRespMono = marketRestSpec.oldTradeLookup(req);
        log.info(JSONObject.toJSONString(oldTradeLookupRespMono.collectList().block()));
        
    }
    @Test
    void testRecentTradesList() throws InterruptedException {
        RecentTradesListReq req = new RecentTradesListReq();
        req.setSymbol("BTCUSDT");
        req.setLimit(10);
        Flux<RecentTradesListResp> oldTradeLookupRespMono = marketRestSpec.recentTradesList(req);
        System.out.println( oldTradeLookupRespMono.collectList().block());
        Thread.sleep(10000);
    }

    @Test
    void testRecentTradesList1() throws InterruptedException {
        RecentTradesListReq req = new RecentTradesListReq();
        req.setSymbol("BTCUSDT");
        req.setLimit(10);
        //异步非阻塞式处理多个结果对象(List)响应
        WebClient webClient = WebClient.create("https://testnet.binance.vision");
        Flux<RecentTradesListResp> flux = webClient.get().uri("/api/v3/trades",req).retrieve().bodyToFlux(RecentTradesListResp.class);
        flux.subscribe(System.out::println);
        System.out.println("testGetList done");
        Thread.sleep(10000);
    }
}
