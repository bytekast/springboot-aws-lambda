package com.serverless.service

import com.serverless.model.Request
import com.serverless.model.Response
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j
import org.springframework.stereotype.Component

@Component
@Log4j
@CompileStatic
class Service {

  Response respond(final Request request) {
    return Response.builder()
        .message('Go Serverless v1.x! Your function executed successfully!')
        .input(request.input)
        .build()
  }
}
