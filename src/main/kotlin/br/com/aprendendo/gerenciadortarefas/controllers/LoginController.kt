package br.com.aprendendo.gerenciadortarefas.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/login")
class LoginController {

    @GetMapping
    fun olaMundo(): String {
        return "Olá mundo"
    }

}