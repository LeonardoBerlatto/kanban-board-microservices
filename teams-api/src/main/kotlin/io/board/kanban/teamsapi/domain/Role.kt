package io.board.kanban.teamsapi.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "roles")
data class Role(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String
)
