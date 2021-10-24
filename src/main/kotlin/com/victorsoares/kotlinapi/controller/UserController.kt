package com.victorsoares.kotlinapi.controller

import com.victorsoares.kotlinapi.entity.User
import com.victorsoares.kotlinapi.repository.UserRepository
import com.victorsoares.kotlinapi.requests.LoginRequest
import com.victorsoares.kotlinapi.requests.UserRequest
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController (
    private val userRepository : UserRepository
    )
{
    @GetMapping
    fun getAllUsers() : ResponseEntity<List<User>> {
        val users = userRepository.findAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getOneTask(@PathVariable("id") id: String): ResponseEntity<User> {
        val user = userRepository.findOneById(ObjectId(id))
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(@RequestBody request: UserRequest) : ResponseEntity<User> {
        val password = BCryptPasswordEncoder().encode(request.senha)
        val user = userRepository.save(User(
            nome = request.nome,
            email = request.email,
            senha = password
        ))

        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @PostMapping("/login" )
    fun login(@RequestBody request: LoginRequest) : ResponseEntity<User> {
        val user = userRepository.findByEmail(request.email)
        val password = BCryptPasswordEncoder().matches(request.senha, user.senha)
        if(password == true){
            return ResponseEntity.ok(user)
        }else {
            return ResponseEntity(user, HttpStatus.BAD_REQUEST)
        }

    }
}