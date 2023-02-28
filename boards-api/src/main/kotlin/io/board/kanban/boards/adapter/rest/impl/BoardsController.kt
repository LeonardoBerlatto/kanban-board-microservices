package io.board.kanban.boards.adapter.rest.impl

import io.board.kanban.boards.adapter.mapper.BoardMapper
import io.board.kanban.boards.adapter.representation.BoardRequest
import io.board.kanban.boards.adapter.representation.BoardResponse
import io.board.kanban.boards.adapter.rest.BoardsApi
import io.board.kanban.boards.domain.service.BoardService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/boards")
class BoardsController(
    val boardService: BoardService,
    val mapper: BoardMapper
): BoardsApi {

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: UUID): BoardResponse {
        val board = boardService.findById(id)
        return mapper.toResponse(board)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun create(@Valid @RequestBody request: BoardRequest): BoardResponse {
        val board = boardService.create(mapper.toDomain(request))
        return mapper.toResponse(board)
    }
}
