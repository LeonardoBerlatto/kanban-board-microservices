package io.board.kanban.teamsapi.repository

import io.board.kanban.teamsapi.domain.Team
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID


interface TeamRepository {
    fun findByName(name: String, pageable: Pageable): Page<Team>
    fun save(team: Team): Team
    fun findById(id: UUID): Team?
}
