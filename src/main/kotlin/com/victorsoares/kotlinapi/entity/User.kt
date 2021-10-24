package com.victorsoares.kotlinapi.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field

class User (
    @Id
    val id: ObjectId = ObjectId.get(),
    @Field
    val nome: String,
    @Field
    val email: String,
    @Field
    val senha: String
)