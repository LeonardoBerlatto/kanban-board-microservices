package io.board.kanban.teamsapi.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Role(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String
)
