package io.board.kanban.teams.domain

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
data class MemberId(
    val userId: UUID,
    val team: Team
) : Serializable
