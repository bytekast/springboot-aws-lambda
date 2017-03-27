package com.serverless.handler

import com.serverless.lambda.Request
import com.serverless.lambda.Response
import com.serverless.persistence.entity.User
import com.serverless.persistence.repository.UserRepository
import groovy.json.JsonOutput
import groovy.util.logging.Log4j
import io.vertx.core.eventbus.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
@Log4j
class CreateUser implements MessageHandler {

  @Autowired
  private UserRepository userRepository

  @PostConstruct
  void init() {
    // Sample Data
    userRepository.save(new User('Michael', 'Jordan'))
    userRepository.save(new User('Scottie', 'Pippen'))
    userRepository.save(new User('Phil', 'Jackson'))
  }

  @Override
  String getRouteChannel() {
    return 'POST:/users/create'
  }

  @Override
  void handle(final Message<Request> message) {
    final Request request = message.body()
    def firstName = request.queryString('firstName')
    def lastName = request.queryString('lastName')
    def user = new User(firstName, lastName)
    userRepository.save(user)
    def response = Response.builder()
        .statusCode(201)
        .body(JsonOutput.prettyPrint(JsonOutput.toJson(user)))
        .build()
    message.reply(response)
  }
}
