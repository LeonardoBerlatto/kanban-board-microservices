package io.board.kanban.boards.domain.repository

import java.util.UUID

interface TeamRepository {
    fun existsById(id: UUID): Boolean

}
