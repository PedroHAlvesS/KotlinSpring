package br.com.aprendendo.gerenciadortarefas.repositories

import br.com.aprendendo.gerenciadortarefas.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
     fun findByEmail(email: String): UserEntity?
}