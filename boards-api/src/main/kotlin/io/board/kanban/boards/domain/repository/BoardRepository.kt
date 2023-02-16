package io.board.kanban.boards.domain.repository

import io.board.kanban.boards.domain.entity.Board

interface BoardRepository {
    fun save(board: Board): Board

}
