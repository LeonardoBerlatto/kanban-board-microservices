package io.board.kanban.boards.adapter.rest

import io.board.kanban.boards.adapter.mapper.BoardMapper
import io.board.kanban.boards.adapter.representation.BoardRequest
import io.board.kanban.boards.adapter.representation.BoardResponse
import io.board.kanban.boards.domain.service.BoardService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/boards")
class BoardsController(
    val boardService: BoardService,
    val mapper: BoardMapper
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<BoardResponse> {
        val board = boardService.findById(id)
        return ResponseEntity.ok(mapper.toResponse(board))
    }

    @PostMapping
    fun create(@Valid @RequestBody request: BoardRequest): ResponseEntity<BoardResponse> {
        val board = boardService.create(mapper.toDomain(request))
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(mapper.toResponse(board))
    }
}
