package me.changchao.spring.retrytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FooService {
  volatile int counter;

  @Retryable(
      value = {Exception.class},
      maxAttempts = 5,
      stateful = true,
      backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 10000))
  public String foo(String name) {
    counter++;
    log.info("called times={}", counter);
    throw new RuntimeException("failed");
  }

  @Recover
  public String recover(Exception e, String str1) {
    log.info("recover called when times={}", counter);
    return "recovered";
  }
}
