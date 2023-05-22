package com.g.fod.apps.main.bench.context;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.g.fod.apps.main.bench.core.MemoryUserRepository;
import com.g.fod.apps.main.bench.core.RespProcessorHandler;
import com.g.fod.apps.main.bench.core.UserRepository;
import com.g.fod.endpoints.mexc.endpoints.CancelByOrderIdEndpoint.CancelReq;
import com.g.fod.endpoints.mexc.endpoints.OrderOps;
import com.g.fod.endpoints.x.ws.OrderResp;
import com.g.fod.endpoints.x.ws.UserAble;
import com.g.fod.endpoints.x.ws.XWSClient;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import com.google.common.util.concurrent.RateLimiter;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.FatalExceptionHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.g.fod.apps.main.bench.core.User;
import com.g.fod.apps.main.bench.properties.XApiProperties;
import com.g.fod.endpoints.mexc.MexcApiSpec;
import com.g.fod.apps.main.endpoints.ws.DisruptorWebSocketHandler;
import com.p.common.base.DisruptorFactory;
import com.p.common.base.domain.OrderId;
import com.p.common.base.domain.UserId;
import com.p.common.base.web.WebClientFactory;
import com.timgroup.statsd.StatsDClient;
import org.reactivestreams.Publisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@ConditionalOnProperty(value = "s", havingValue = "1")
public class UserContext implements AutoCloseable {

  private final XApiProperties xApiProperties;
  @Getter
  private final Map<UserId, UserRepository> userRepositoryMap;
  @Getter
  private final Map<UserId, XWSClient> xwsClientMap;
  @Getter
  private final Map<UserId, MexcApiSpec> orderOperatorMap;
  private final Disruptor<XWSDataEvent> disruptor;
  private final List<User> user;
  private final Random random = new Random();

  private final static Map<Long, String> symbolMap = new HashMap<>();

  static {
    symbolMap.put(70005L, "FILUSDT");
    symbolMap.put(70014L, "SUSHIUSDT");
    symbolMap.put(70015L, "UNIUSDT");
    symbolMap.put(70002L, "BTCUSDT");
    symbolMap.put(70001L, "ETHUSDT");
    symbolMap.put(70004L, "LINKUSDT");
    symbolMap.put(70003L, "BCHUSDT");
  }

  public UserContext(XApiProperties xApiProperties, StatsDClient dClient,
    RateLimiter rateLimiter)
    throws Exception {
    this.xApiProperties = xApiProperties;

    user = this.xApiProperties.getUsers();

    disruptor = DisruptorFactory.single(XWSDataEvent::new, 1024 * 8, "XWS");
    disruptor.setDefaultExceptionHandler(new FatalExceptionHandler());

    userRepositoryMap = initUserRepository(dClient);
    xwsClientMap = initWSClient();
    orderOperatorMap = initOrderOperator();

    this.disruptor.handleEventsWith(new XWSDataDeserializeHandler(dClient))
      .then(new EventHandler[]{new RespProcessorHandler((resp, event) -> {
        try {
          if (resp instanceof UserAble) {
            Long userId = ((UserAble) resp).getUserId().getValue();
            if (resp instanceof OrderResp) {
              final UserRepository repository = userRepositoryMap.get(userId);
              Map<OrderId, Long> orderIds = new HashMap<>();//resp.exec(repository);

              int count = orderIds.size() - 50;
              if (count <= 0) {return;}

              if (log.isDebugEnabled()) {
                log.debug("[S]{}:{}", userId, orderIds.size());
              }

              int rate = count < 50 ?
                10 : (count < 100 ? 20 : (count < 150 ? 50
                : (count < 200 ? 70 : (count < 300 ? 90 : 100))));

              // cancel orders
              Flux.fromIterable(orderIds.entrySet())
                .filter(r -> rateLimiter.tryAcquire(Duration.ofNanos(100)))
                .flatMap(
                  (Function<Entry<OrderId, Long>, Publisher<Long>>) entry -> {
                    if (rate == 0) {return Mono.empty();}
                    if (log.isDebugEnabled()) {
                      log.debug("[Cancel-Rate] {}", rate);
                    }
                    if (random.nextInt(100) > rate) {return Mono.empty();}

                    try {
                      val orderId = entry.getKey();
                      val instrumentId = entry;
                      CancelReq cancelReq =
                        OrderOps.CANCEL.emit(symbolMap.get(instrumentId),
                          req -> req.ordId(orderId));

                      if (log.isDebugEnabled()) {
                        log.debug(" x< {}", cancelReq);
                      }

                      repository.remove(orderId);
                      val orderOperator = orderOperatorMap.get(userId);

                      return null;
//                                            return cancelReq.exec(orderOperator)
//                                                .flatMap(new Function<Long, Mono<Long>>() {
//                                                    @Override
//                                                    public Mono<Long> apply(Long aLong) {
//                                                        repository.cancel(orderId,
//                                                            System.nanoTime());
//                                                        repository.cancelMills(orderId,
//                                                            System.currentTimeMillis());
//                                                        return Mono.just(aLong);
//                                                    }
//                                                });
                    } catch (Exception e) {
                      log.error(e.getMessage(), e);
                    }
                    return Mono.empty();
                  }).subscribe(latency -> {
                  if (latency > 0L) {
                    String type = CancelReq.class.getSimpleName();
                    if (log.isTraceEnabled()) {
                      log.trace("round.trip.to_ack={},type={}", latency, type);
                    }
                    dClient.time("round.trip.to_ack", latency, "type:" + type);
                  }
                });
            }
          }
        } catch (Exception e) {
          log.info("RESP:{},ERR:{}", resp, e.getLocalizedMessage());
        }
      })});

    disruptor.start();
  }

  private Map<UserId, MexcApiSpec> initOrderOperator() {
    return this.user.stream().distinct().collect(Collectors.toMap(
      User::getUid,
      user1 -> new MexcApiSpec(WebClientFactory.create(this.xApiProperties.getBaseUri(),
        httpHeaders -> {
          httpHeaders.add("api-key", user1.getApiKey());
          httpHeaders.add("Content-Type", "application/json");
          httpHeaders.add("RealClientIP", "127.0.0.1");
          httpHeaders.add("Connection", "Keep-Alive");
          httpHeaders.add("Keep-Alive", "timeout=5, max=5000");
        }), null)));
  }

  private Map<UserId, UserRepository> initUserRepository(StatsDClient dClient) {
    return user.stream()
      .collect(Collectors.toMap(e -> e.getUid(), e -> new MemoryUserRepository(dClient)));
  }

  private Map<UserId, XWSClient> initWSClient() throws Exception {
    val xwsClientMap = new HashMap<UserId, XWSClient>(user.size());
    for (User u : user) {
      XWSClient client = new XWSClient(new DisruptorWebSocketHandler(disruptor),
        this.xApiProperties.getWsUri(),
        this.xApiProperties.getTimeoutEpochMill(), "");
      client.start();
      client.login(u.getApiKey(), u.getUid().getValue(), "!@order");
      xwsClientMap.put(u.getUid(), client);
    }
    return xwsClientMap;
  }

  @Override
  public void close() throws Exception {
    disruptor.shutdown(5, TimeUnit.SECONDS);
  }
}
