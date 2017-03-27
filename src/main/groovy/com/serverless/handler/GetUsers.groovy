package com.serverless.handler

import com.serverless.lambda.Request
import com.serverless.lambda.Response
import com.serverless.persistence.repository.UserRepository
import groovy.json.JsonOutput
import groovy.util.logging.Log4j
import io.vertx.core.eventbus.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Log4j
class GetUsers implements MessageHandler {

  @Autowired
  private UserRepository userRepository

  @Override
  String getRouteChannel() {
    return 'GET:/users'
  }

  @Override
  void handle(final Message<Request> message) {
    def allCustomers = userRepository.findAll()
    def response = Response.builder()
        .statusCode(200)
        .body(JsonOutput.prettyPrint(JsonOutput.toJson(allCustomers)))
        .build()
    message.reply(response)
  }
}
