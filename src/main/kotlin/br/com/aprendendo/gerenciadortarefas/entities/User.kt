package br.com.aprendendo.gerenciadortarefas.entities

data class User (
        val id: Long,
        val nome: String,
        val email: String,
        val senha: String
 )