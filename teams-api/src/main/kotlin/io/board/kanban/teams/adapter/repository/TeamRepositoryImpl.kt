package io.board.kanban.teams.adapter.repository

import io.board.kanban.teams.domain.entity.Team
import io.board.kanban.teams.domain.repository.TeamRepository
import io.board.kanban.teams.adapter.repository.jpa.JpaTeamRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TeamRepositoryImpl(
    private val jpaRepository: JpaTeamRepository
) : TeamRepository {

    @Cacheable("teams", key = "#name")
    override fun findByName(name: String, pageable: Pageable): Page<Team> {
        return jpaRepository.findByNameContains(name, pageable)
    }

    @CacheEvict("teams", allEntries = true)
    override fun save(team: Team): Team {
        return jpaRepository.save(team)
    }

    @Cacheable("teams", key = "#id", unless = "#result == null")
    override fun findById(id: UUID): Team? {
        return jpaRepository.findById(id).orElse(null)
    }
}