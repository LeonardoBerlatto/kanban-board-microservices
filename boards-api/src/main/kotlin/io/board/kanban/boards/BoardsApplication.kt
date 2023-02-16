package io.board.kanban.boards

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class BoardsApplication

fun main(args: Array<String>) {
	runApplication<BoardsApplication>(*args)
}
