package io.board.kanban.teams

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TeamsApiApplication

fun main(args: Array<String>) {
	runApplication<TeamsApiApplication>(*args)
}
