package com.g.fod.apps.rt;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import com.g.fod.apps.rt.exception.SlaveQueryEmptyException;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import reactor.util.retry.Retry;

class FluxOperatorTests {

  @Test
  void handAndRetryTest() throws InterruptedException {
    Mono.just(1L).handle(new BiConsumer<Long, SynchronousSink<Long>>() {
        private int i = 0;

        @Override
        public void accept(Long aLong, SynchronousSink<Long> synchronousSink) {
          System.out.println("handle " + aLong);
          i++;
          if (i > 10) {synchronousSink.complete();}
          if (i % 2 == 0) {
            synchronousSink.next(aLong + 100);
          } else {synchronousSink.error(new SlaveQueryEmptyException());}
        }
      }).retryWhen(
        Retry.backoff(3, Duration.ofNanos(200)).jitter(0.75).filter(e -> e instanceof SlaveQueryEmptyException)
          .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> new RuntimeException()))
      .doOnError(throwable -> System.out.println("doOnError" + throwable.getMessage()))
      .subscribe(System.out::println);

    TimeUnit.SECONDS.sleep(1);
  }

  @Test
  void doOnSuccessTest() throws InterruptedException {
    Mono.just(1L).doOnSuccess(aLong -> System.out.println("onSuccess" + aLong)).subscribe(System.out::println);
    TimeUnit.MILLISECONDS.sleep(100);
  }
}
