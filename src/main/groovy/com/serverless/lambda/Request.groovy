package com.serverless.lambda

import com.amazonaws.services.lambda.runtime.Context
import groovy.transform.ToString

@ToString(includePackage = false)
class Request {
  private final Map input
  private final Context context

  Request(final Map input, final Context context) {
    this.input = input
    this.context = context
  }

  String requestId() {
    context?.awsRequestId
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
}
