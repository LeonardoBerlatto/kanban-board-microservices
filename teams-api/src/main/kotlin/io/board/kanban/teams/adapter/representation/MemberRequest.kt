package io.board.kanban.teams.adapter.representation

import java.util.UUID


class MemberRequest(
    val userId: UUID,
    val teamId: UUID,
    val roleId: UUID
)
