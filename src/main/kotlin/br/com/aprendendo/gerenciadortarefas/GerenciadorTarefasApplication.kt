package br.com.aprendendo.gerenciadortarefas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GerenciadorTarefasApplication

fun main(args: Array<String>) {
	runApplication<GerenciadorTarefasApplication>(*args)
}
