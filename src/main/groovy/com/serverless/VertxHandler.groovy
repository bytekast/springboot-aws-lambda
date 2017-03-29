package com.serverless

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import groovy.transform.Memoized
import groovy.util.logging.Log4j
import io.vertx.core.Vertx

import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

@Log4j
class VertxHandler implements RequestHandler<Map, Map> {

  @Override
  Map handleRequest(Map input, Context context) {
    CompletableFuture<Map> future = new CompletableFuture<>()
    def address = "${input.httpMethod}:${input.resource}"
    vertx().eventBus().send(address, input, { ar ->
      if (ar.succeeded()) {
        log.info "Received reply: ${ar.result().body()}"
        future.complete(ar.result().body())
      }
    })
    future.get(5, TimeUnit.SECONDS)
  }

  @Memoized
  Vertx vertx() {
    System.setProperty('vertx.disableFileCPResolving', 'true')
    def vertx = Vertx.vertx()
    def eventBus = vertx.eventBus()

    eventBus.consumer('GET:/users') { message ->
      message.reply([statusCode: 200, body: 'GET:/users'])
    }

    eventBus.consumer('GET:/users/{lastName}') { message ->
      message.reply([statusCode: 200, body: 'GET:/users/{lastName}'])
    }

    eventBus.consumer('POST:/users/create') { message ->
      message.reply([statusCode: 201, body: 'POST:/users/create'])
    }

    return vertx
  }
}
