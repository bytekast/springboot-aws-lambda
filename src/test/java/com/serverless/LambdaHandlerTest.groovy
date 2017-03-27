package com.serverless

import com.amazonaws.services.lambda.runtime.Context
import groovy.util.logging.Log4j
import org.junit.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = "com.serverless")
@Log4j
class LambdaHandlerTest {
  @Test
  void handleRequest() throws Exception {

    LambdaHandler mainHandler = new LambdaHandler()

    def input
    def context = {} as Context
    context.metaClass.getAwsRequestId = { UUID.randomUUID().toString() }


    input = [httpMethod: 'GET', resource: '/users/{lastName}', pathParameters: [lastName: 'Jackson']]
    mainHandler.handleRequest(input, context)

    input = [httpMethod           : 'POST', resource: '/users/create',
             queryStringParameters: [firstName: 'Rowell', lastName: 'Belen']]
    mainHandler.handleRequest(input, context)

    input = [httpMethod: 'GET', resource: '/users']
    mainHandler.handleRequest(input, context)
  }
}