package io.board.kanban.boards.adapter.rest

import io.board.kanban.boards.adapter.mapper.BoardMapper
import io.board.kanban.boards.adapter.representation.BoardRequest
import io.board.kanban.boards.adapter.representation.BoardResponse
import io.board.kanban.boards.domain.service.BoardService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/boards")
class BoardsController(
    val boardService: BoardService,
    val mapper: BoardMapper
) {

    @PostMapping
    fun createBoard(@Valid @RequestBody request: BoardRequest): ResponseEntity<BoardResponse> {
        val board = boardService.create(mapper.toDomain(request))
        return ResponseEntity.ok(mapper.toResponse(board))
    }
}
