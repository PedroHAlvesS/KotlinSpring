package br.com.aprendendo.gerenciadortarefas.dtos

data class UserDTO(
        val nome: String,
        val email: String,
        var senha: String
)
