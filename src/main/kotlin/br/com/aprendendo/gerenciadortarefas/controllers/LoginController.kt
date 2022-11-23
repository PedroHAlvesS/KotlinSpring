package br.com.aprendendo.gerenciadortarefas.controllers

import br.com.aprendendo.gerenciadortarefas.dto.ErroDTO
import br.com.aprendendo.gerenciadortarefas.dto.LoginDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/login")
class LoginController {

    @GetMapping
    fun olaMundo(): String {
        return "Olá mundo"
    }

    @PostMapping
    fun efetuarLogin(@RequestBody loginDTO: LoginDTO): ResponseEntity<Any> {
        try {
            if (loginDTO == null || loginDTO.login.isNullOrBlank() || loginDTO.login.isNullOrEmpty()
                    || loginDTO.senha.isNullOrBlank() || loginDTO.senha.isNullOrEmpty()) {

                return ResponseEntity(ErroDTO(HttpStatus.BAD_REQUEST.value(), "Parâmetros de entrada inválidos"), HttpStatus.BAD_REQUEST)

            }
            return ResponseEntity.ok("Logado")
        } catch (e: Exception) {
            return ResponseEntity(ErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Não foi possível efetuar o login"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}