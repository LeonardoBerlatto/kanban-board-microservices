package io.board.kanban.teams.adapter.repository

import io.board.kanban.teams.domain.entity.Role
import io.board.kanban.teams.domain.repository.RoleRepository
import io.board.kanban.teams.adapter.repository.jpa.JpaRoleRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RoleRepositoryImpl(private val jpaRoleRepository: JpaRoleRepository) : RoleRepository {

    @Cacheable("roles", key = "#root.methodName", unless = "#result == null")
    override fun findAll(): List<Role> {
        return jpaRoleRepository.findAll()
    }

    @Cacheable("roles", key = "#root.methodName - #roleId", unless = "#result == null")
    override fun findById(roleId: UUID): Role? {
        return jpaRoleRepository.findById(roleId).orElse(null)
    }
}