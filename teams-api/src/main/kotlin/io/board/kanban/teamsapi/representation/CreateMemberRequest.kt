package io.board.kanban.teamsapi.representation

import java.util.UUID


class CreateMemberRequest(
    val userId: UUID,
    val teamId: UUID,
    val roleId: UUID
)
