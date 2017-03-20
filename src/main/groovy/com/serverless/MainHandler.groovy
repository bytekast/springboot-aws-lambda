package com.serverless

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.model.Request
import com.serverless.model.Response
import com.serverless.service.Service
import groovy.util.logging.Log4j
import org.springframework.context.ApplicationContext

@Log4j
class MainHandler implements RequestHandler<Map, Response> {

  private ApplicationContext applicationContext = Application.newInstance().applicationContext()

  @Override
  Response handleRequest(Map input, Context context) {
    log.info("received: " + input)
    final Service service = applicationContext.getBean(Service.class)
    return service.respond(Request.builder().input(input).context(context).build())
  }
}
