package io.board.kanban.boards.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "boards")
data class Board(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val teamId: String,
    val issues: List<UUID>
)