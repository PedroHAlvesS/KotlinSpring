package br.com.aprendendo.gerenciadortarefas.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class UserEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 1L,
        val nome: String,
        val email: String,
        val senha: String
 )