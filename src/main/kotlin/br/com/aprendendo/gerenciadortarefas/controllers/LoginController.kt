package br.com.aprendendo.gerenciadortarefas.controllers

import br.com.aprendendo.gerenciadortarefas.dtos.ErroDTO
import br.com.aprendendo.gerenciadortarefas.dtos.LoginDTO
import br.com.aprendendo.gerenciadortarefas.dtos.LoginResponseDTO
import br.com.aprendendo.gerenciadortarefas.utils.JWTUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/login")
class LoginController {

   private val LOGIN_TESTE = "admin"
   private val SENHA_TESTE = "123"

    @PostMapping
    fun efetuarLogin(@RequestBody loginDTO: LoginDTO): ResponseEntity<Any> {
        try {
            if (loginDTO == null || loginDTO.login.isNullOrBlank() || loginDTO.login.isNullOrEmpty()
                    || loginDTO.senha.isNullOrBlank() || loginDTO.senha.isNullOrEmpty()
                    || loginDTO.senha != SENHA_TESTE || loginDTO.login != LOGIN_TESTE) {

                return ResponseEntity(ErroDTO(HttpStatus.BAD_REQUEST.value(), "Parâmetros de entrada inválidos"), HttpStatus.BAD_REQUEST)

            }
            val idUser = "1"
            val token = JWTUtils().generateToken(idUser)

            val userTest = LoginResponseDTO("usuario teste", "admin@admin.com", token)
            return ResponseEntity.ok(userTest)
        } catch (e: Exception) {
            return ResponseEntity(ErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Não foi possível efetuar o login"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}