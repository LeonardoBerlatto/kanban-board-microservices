package io.board.kanban.boards.adapter.representation

import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class BoardRequest(
    @NotBlank(message = "Board name is required")
    val name: String,
    @NotBlank(message = "Board description is required")
    val description: String,
    @NotBlank(message = "Board teamId is required")
    val teamId: UUID,
    val issuesIds: List<UUID>
)
