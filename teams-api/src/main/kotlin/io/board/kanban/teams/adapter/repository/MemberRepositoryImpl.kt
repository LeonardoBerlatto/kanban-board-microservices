package io.board.kanban.teams.adapter.repository

import io.board.kanban.teams.domain.entity.Member
import io.board.kanban.teams.domain.entity.MemberId
import io.board.kanban.teams.domain.entity.Team
import io.board.kanban.teams.domain.repository.MemberRepository
import io.board.kanban.teams.adapter.repository.jpa.JpaMemberRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(val jpaRepository: JpaMemberRepository) : MemberRepository {

    @Cacheable("members", key = "#root.methodName - #id", unless = "#result == null")
    override fun findById(id: MemberId): Member? {
        return jpaRepository.findById(id).orElse(null)
    }

    @Cacheable("members", key = "#root.methodName - #id", unless = "#result == null")
    override fun existsById(id: MemberId): Boolean {
        return jpaRepository.existsById(id)
    }

    override fun findByTeam(team: Team): List<Member> {
        return jpaRepository.findByTeam(team)
    }

    @CacheEvict("members", allEntries = true)
    override fun save(member: Member): Member {
        return jpaRepository.save(member)
    }

    @CacheEvict("members", allEntries = true)
    override fun deleteById(id: MemberId) {
        return jpaRepository.deleteById(id)
    }
}