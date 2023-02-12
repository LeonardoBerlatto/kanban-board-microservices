package io.board.kanban.boards.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Entity
@Document(collection = "boards")
data class Board(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val teamId: String,
    val issues: List<UUID>
)