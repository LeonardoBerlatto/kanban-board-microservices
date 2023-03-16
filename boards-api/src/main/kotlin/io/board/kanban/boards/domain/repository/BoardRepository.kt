package io.board.kanban.boards.domain.repository

import io.board.kanban.boards.domain.entity.Board
import java.util.Optional
import java.util.UUID

interface BoardRepository {
    fun findById(id: UUID): Optional<Board>
    fun save(board: Board): Board
    fun findAll(): List<Board>
}
