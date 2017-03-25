package com.serverless.handlers

import com.serverless.lambda.Request
import com.serverless.lambda.Response

interface Handler {
  boolean route(Request request)
  Response respond(Request request)
}