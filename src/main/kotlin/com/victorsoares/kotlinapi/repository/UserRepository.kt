package com.victorsoares.kotlinapi.repository

import com.victorsoares.kotlinapi.entity.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findOneById(id: ObjectId): User
    fun findByEmail(email: String) : User
}