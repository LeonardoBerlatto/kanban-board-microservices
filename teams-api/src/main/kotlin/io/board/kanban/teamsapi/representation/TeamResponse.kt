package io.board.kanban.teamsapi.representation

import java.util.UUID


data class TeamResponse(
    val id: UUID,
    val name: String,
    val members: List<MemberResponse>
)



