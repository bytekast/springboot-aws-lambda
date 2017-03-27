package com.serverless.handler

import com.serverless.lambda.Request
import io.vertx.core.Handler
import io.vertx.core.eventbus.Message


interface MessageHandler extends Handler<Message<Request>> {
  String getRouteChannel()
}