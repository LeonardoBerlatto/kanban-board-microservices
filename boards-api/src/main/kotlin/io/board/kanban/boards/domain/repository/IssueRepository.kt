package io.board.kanban.boards.domain.repository

import java.util.UUID

interface IssueRepository {
    fun existById(id: UUID): Boolean

}
