package me.changchao.spring.retrytest;

import lombok.extern.slf4j.Slf4j;
import org.omg.IOP.ExceptionDetailMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@Slf4j
public class RetryTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(RetryTestApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(FooService service) {
    return args -> {
      for (int i = 0; i < 10; i++) {
        try {
          String hello = service.foo("hello");
          log.info("result={}", hello);
        } catch (Exception e) {
          log.error("failed in foo");
        }
      }
    };
  }
}
