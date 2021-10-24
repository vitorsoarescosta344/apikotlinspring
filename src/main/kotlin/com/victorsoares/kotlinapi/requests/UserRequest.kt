package com.victorsoares.kotlinapi.requests

class UserRequest (


    val nome: String,
    val email: String,
    val senha: String
)

class LoginRequest (
    val email: String,
    val senha: String
    )