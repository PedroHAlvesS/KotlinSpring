package br.com.aprendendo.gerenciadortarefas.utils

import br.com.aprendendo.gerenciadortarefas.dtos.UserDTO
import br.com.aprendendo.gerenciadortarefas.entities.UserEntity
import org.springframework.stereotype.Component

@Component
class Mapper {

    fun userDtoToEntity(userDTO: UserDTO): UserEntity {
        return UserEntity(nome = userDTO.nome, email = userDTO.email, senha = userDTO.senha)
    }

}