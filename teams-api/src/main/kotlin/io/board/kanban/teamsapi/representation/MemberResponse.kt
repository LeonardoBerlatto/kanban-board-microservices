package io.board.kanban.teamsapi.representation

import java.util.UUID

data class MemberResponse(
    val userId: UUID,
    val teamId: UUID,
    val roleId: UUID,
)
