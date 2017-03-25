package com.serverless.persistence.repositories

import com.serverless.persistence.entities.User
import groovy.transform.CompileStatic
import org.springframework.data.repository.CrudRepository

@CompileStatic
interface UserRepository extends CrudRepository<User, Long> {

  List<User> findByLastName(String lastName)
}