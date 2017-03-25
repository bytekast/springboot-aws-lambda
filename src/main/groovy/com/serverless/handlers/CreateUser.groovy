package com.serverless.handlers

import com.serverless.lambda.Request
import com.serverless.lambda.Response
import com.serverless.persistence.entities.User
import com.serverless.persistence.repositories.UserRepository
import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
@Scope("prototype")
@Log4j
@CompileStatic
class CreateUser implements Handler {

  @Autowired
  private UserRepository userRepository

  @Override
  boolean route(Request request) {
    request.resourcePath() == '/users/create' && request.httpMethod() == 'POST'
  }

  @Override
  Response respond(final Request request) {
    def firstName = request.queryString('firstName')
    def lastName = request.queryString('lastName')
    def user = new User(firstName, lastName)
    userRepository.save(user)
    Response.builder()
        .statusCode(201)
        .body(JsonOutput.prettyPrint(JsonOutput.toJson(user)))
        .build()
  }

  @PostConstruct
  void init() {
    userRepository.save(new User('Michael', 'Jordan'))
    userRepository.save(new User('Scottie', 'Pippen'))
    userRepository.save(new User('Phil', 'Jackson'))
  }
}
