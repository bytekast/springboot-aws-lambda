package com.serverless.model

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
@CompileStatic
class Response {
  Object message
  Map input
}
