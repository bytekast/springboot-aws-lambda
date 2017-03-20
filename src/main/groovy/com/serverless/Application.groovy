package com.serverless

import groovy.transform.Memoized
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = "com.serverless")
class Application {

  static void main(String[] args) throws Exception {
    Application.newInstance().applicationContext(args)
  }

  @Memoized
  ApplicationContext applicationContext(final String[] args = []) {
    SpringApplication.run(Application.class, args)
  }
}
