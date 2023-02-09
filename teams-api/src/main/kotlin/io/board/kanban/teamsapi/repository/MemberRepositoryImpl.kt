package io.board.kanban.teamsapi.repository

import io.board.kanban.teamsapi.domain.Member
import io.board.kanban.teamsapi.domain.MemberId
import io.board.kanban.teamsapi.domain.Team
import io.board.kanban.teamsapi.repository.jpa.JpaMemberRepository
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(val jpaRepository: JpaMemberRepository) : MemberRepository {
    override fun existsById(id: MemberId): Boolean {
        return jpaRepository.existsById(id)
    }

    override fun save(member: Member): Member {
        return jpaRepository.save(member)
    }

    override fun findByTeam(team: Team): List<Member> {
        return jpaRepository.findByTeam(team)
    }
}