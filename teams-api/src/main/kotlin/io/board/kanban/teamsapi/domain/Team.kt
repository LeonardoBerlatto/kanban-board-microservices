package io.board.kanban.teamsapi.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class Team(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String
)
