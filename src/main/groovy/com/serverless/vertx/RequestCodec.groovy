package com.serverless.vertx

import com.serverless.lambda.Request
import groovy.json.JsonSlurper
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import org.springframework.stereotype.Component

@Component
class RequestCodec implements MessageCodec<Request, Request> {

  @Override
  void encodeToWire(Buffer buffer, Request request) {
    buffer.appendString(request.asJson())
  }

  @Override
  Request decodeFromWire(int pos, Buffer buffer) {
    String jsonString = buffer.getString(0, buffer.length())
    Map json = JsonSlurper.newInstance().parseText(jsonString)
    return new Request(json.requestId, json.input)
  }

  @Override
  Request transform(Request request) {
    request
  }

  @Override
  String name() {
    this.getClass().getSimpleName()
  }

  @Override
  byte systemCodecID() {
    -1
  }
}
