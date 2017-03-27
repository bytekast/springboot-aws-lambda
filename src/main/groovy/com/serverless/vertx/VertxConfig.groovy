package com.serverless.vertx

import com.serverless.handler.MessageHandler
import com.serverless.lambda.Request
import com.serverless.lambda.Response
import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VertxConfig {

  @Bean
  EventBus eventBus(final List<MessageHandler> handlers,
                    final RequestCodec requestCodec,
                    final ResponseCodec responseCodec) {
    def eventBus = Vertx.vertx().eventBus()

    // Register Codecs
    eventBus.registerDefaultCodec(Request.class, requestCodec)
    eventBus.registerDefaultCodec(Response.class, responseCodec)

    // Register Handlers
    handlers?.each {
      eventBus.consumer(it.getRouteChannel(), it)
    }

    eventBus
  }
}
