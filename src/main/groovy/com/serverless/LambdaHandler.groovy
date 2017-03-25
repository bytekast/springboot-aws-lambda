package com.serverless

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.lambda.Dispatcher
import com.serverless.lambda.Request
import com.serverless.lambda.Response
import groovy.transform.Memoized
import groovy.util.logging.Log4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = "com.serverless")
@Log4j
class LambdaHandler implements RequestHandler<Map, Response> {

  static void main(String[] args) throws Exception {
    LambdaHandler.newInstance().getApplicationContext(args)
  }

  @Memoized
  ApplicationContext getApplicationContext(String[] args = []) {
    return SpringApplication.run(LambdaHandler.class, args)
  }

  @Override
  Response handleRequest(Map input, Context context) {
    final Request request = new Request(input, context)
    Dispatcher dispatcher = getApplicationContext().getBean(Dispatcher.class)
    return dispatcher.dispatch(request)
  }
}
