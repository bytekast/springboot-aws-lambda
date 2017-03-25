package com.serverless.handlers


import com.serverless.lambda.Request
import com.serverless.lambda.Response
import com.serverless.persistence.repositories.UserRepository
import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Log4j
@CompileStatic
class FindUsersByLastName implements Handler {

  @Autowired
  private UserRepository userRepository

  @Override
  boolean route(final Request request) {
    request.resourcePath() == '/users/{lastName}' && request.httpMethod() == 'GET'
  }

  @Override
  Response respond(final Request request) {
    def customers = userRepository.findByLastName(request.pathParameter('lastName'))
    Response.builder()
        .statusCode(200)
        .body(JsonOutput.prettyPrint(JsonOutput.toJson(customers)))
        .build()
  }
}
