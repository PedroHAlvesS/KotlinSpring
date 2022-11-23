package br.com.aprendendo.gerenciadortarefas.controllers

import br.com.aprendendo.gerenciadortarefas.dtos.ErroDTO
import br.com.aprendendo.gerenciadortarefas.dtos.UserDTO
import br.com.aprendendo.gerenciadortarefas.repositories.UserRepository
import br.com.aprendendo.gerenciadortarefas.utils.Cryptography
import br.com.aprendendo.gerenciadortarefas.utils.Mapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("api/user")
class UserController(val userRepository: UserRepository, val cryptography: Cryptography, val mapper: Mapper) {

    @PostMapping
    fun createUser(@RequestBody userDto: UserDTO): Any {
        try {
            val erros = mutableListOf<String>()

            if(userDto == null){
                return ResponseEntity(ErroDTO(HttpStatus.BAD_REQUEST.value(), "parâmetros de entrada não enviado"),
                        HttpStatus.BAD_REQUEST)
            }

            if(userDto.nome.isNullOrEmpty() || userDto.nome.isNullOrBlank() || userDto.nome.length < 2){
                erros.add("Nome inválido")
            }

            if(userDto.email.isNullOrEmpty() || userDto.email.isNullOrBlank() || userDto.email.length < 5){
                erros.add("Email inválido")
            }

            if(userDto.senha.isNullOrEmpty() || userDto.senha.isNullOrBlank() || userDto.senha.length < 4){
                erros.add("Senha inválida")
            }

            if(userRepository.findByEmail(userDto.email) != null){
                erros.add("Já existe usuário com o email informado")
            }

            if(erros.size > 0){
                return ResponseEntity(ErroDTO(status = HttpStatus.BAD_REQUEST.value(), erros = erros), HttpStatus.BAD_REQUEST)
            }

            userDto.senha = cryptography.md5(userDto.senha)
            val userEntity = mapper.userDtoToEntity(userDto)
            userRepository.save(userEntity)

            return ResponseEntity.ok("Criado com sucesso")
        } catch (exception: Exception) {
            return ResponseEntity(ErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Não foi possível criar o usuario"),
                    HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}