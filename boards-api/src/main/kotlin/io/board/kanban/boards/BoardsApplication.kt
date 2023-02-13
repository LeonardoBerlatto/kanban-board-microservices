package io.board.kanban.boards

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BoardsApplication

fun main(args: Array<String>) {
	runApplication<BoardsApplication>(*args)
}