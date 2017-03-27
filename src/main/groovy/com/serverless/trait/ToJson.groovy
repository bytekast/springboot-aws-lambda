package com.serverless.trait

import groovy.json.JsonOutput
import groovy.transform.CompileStatic

@CompileStatic
trait ToJson {
  String asJson() {
    return JsonOutput.prettyPrint(JsonOutput.toJson(this))
  }
}