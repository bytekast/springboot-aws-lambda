package com.serverless.persistence.entity

import groovy.transform.CompileStatic
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@ToString
@CompileStatic
class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id
  String firstName
  String lastName

  User() {}

  User(String firstName, String lastName) {
    this.firstName = firstName
    this.lastName = lastName
  }
}