package com.serverless.persistence.repository

import com.serverless.persistence.entity.User
import groovy.transform.CompileStatic
import org.springframework.data.repository.CrudRepository

@CompileStatic
interface UserRepository extends CrudRepository<User, Long> {

  List<User> findByLastName(String lastName)
}