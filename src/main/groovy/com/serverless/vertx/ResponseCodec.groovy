package com.serverless.vertx

import com.serverless.lambda.Response
import groovy.json.JsonSlurper
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import org.springframework.stereotype.Component

@Component
class ResponseCodec implements MessageCodec<Response, Response> {

  @Override
  void encodeToWire(Buffer buffer, Response response) {
    buffer.appendString(response.asJson())
  }

  @Override
  Response decodeFromWire(int pos, Buffer buffer) {
    String jsonString = buffer.getString(0, buffer.length())
    Map json = JsonSlurper.newInstance().parseText(jsonString)
    Response.builder().statusCode(json?.statusCode).body(json.body).headers(json.headers).build()
  }

  @Override
  Response transform(Response response) {
    response
  }

  @Override
  String name() {
    this.getClass().getSimpleName()
  }

  @Override
  byte systemCodecID() {
    return -1
  }
}
