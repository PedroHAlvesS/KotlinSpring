package br.com.aprendendo.gerenciadortarefas.dtos

data class ErroDTO(val status: Int, val erro: String? = null, val erros: List<String>? = null)