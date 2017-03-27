package com.serverless.lambda

import com.serverless.trait.ToJson
import groovy.transform.CompileStatic
import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder
@ToString(includePackage = false)
@CompileStatic
class Response implements ToJson {
  int statusCode
  String body
  private Map<String, String> headers = [:]

  void addHeader(String key, String value) {
    headers.put(key, value)
  }
}
