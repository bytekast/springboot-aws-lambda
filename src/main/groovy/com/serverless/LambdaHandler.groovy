package com.serverless

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.lambda.Request
import com.serverless.lambda.Response
import groovy.transform.Memoized
import groovy.util.logging.Log4j
import io.vertx.core.eventbus.EventBus
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan

import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

@SpringBootApplication
@ComponentScan(basePackages = "com.serverless")
@Log4j
class LambdaHandler implements RequestHandler<Map, Response> {

  static void main(String[] args) throws Exception {
    LambdaHandler.newInstance().getApplicationContext(args)
  }

  @Memoized
  ApplicationContext getApplicationContext(String[] args = []) {
    System.setProperty('vertx.disableFileCPResolving', 'true')
    return SpringApplication.run(LambdaHandler.class, args)
  }

  @Override
  Response handleRequest(Map input, Context context) {
    final Request request = new Request(context.awsRequestId, input)
    def eventBus = applicationContext.getBean(EventBus.class)
    CompletableFuture<Response> future = new CompletableFuture<>()
    eventBus.send(request.methodResource(), request, { ar ->
      if (ar.succeeded()) {
        assert ar.result().body() instanceof Response
        log.info "Received reply: ${ar.result().body()}"
        future.complete(ar.result().body())
      }
    })
    future.get(5, TimeUnit.SECONDS)
  }
}
