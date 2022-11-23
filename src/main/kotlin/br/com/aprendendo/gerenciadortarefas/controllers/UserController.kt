package br.com.aprendendo.gerenciadortarefas.controllers

import br.com.aprendendo.gerenciadortarefas.entities.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/user")
class UserController {

    @GetMapping
    fun getUser() = User(1, "UserTest", "admin@admin", "123")
}