package com.serverless.lambda

import com.serverless.trait.ToJson
import groovy.transform.ToString

@ToString(includePackage = false)
class Request implements ToJson {
  final Map input
  final String requestId

  Request(final String requestId, final Map input) {
    this.input = input
    this.requestId = requestId
  }

  String requestId() {
    requestId
  }

  String resourcePath() {
    input?.resource ?: 'unknown'
  }

  String httpMethod() {
    input?.httpMethod ?: 'unknown'
  }

  String queryString(String name) {
    input?.queryStringParameters?."${name}"?.trim()
  }

  String pathParameter(String name) {
    input?.pathParameters?."${name}"?.trim()
  }

  String methodResource() {
    "${httpMethod()}:${resourcePath()}"
  }
}
