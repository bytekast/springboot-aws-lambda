package com.serverless.model

import com.amazonaws.services.lambda.runtime.Context
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder

@Builder
@CompileStatic
class Request {
  Map input
  Context context
}
