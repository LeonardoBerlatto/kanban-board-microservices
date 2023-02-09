package io.board.kanban.teams.repository

import io.board.kanban.teams.domain.Member
import io.board.kanban.teams.domain.MemberId
import io.board.kanban.teams.domain.Team
import io.board.kanban.teams.repository.jpa.JpaMemberRepository
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(val jpaRepository: JpaMemberRepository) : MemberRepository {
    override fun findById(memberId: MemberId): Member? {
        return jpaRepository.findById(memberId).orElse(null)
    }

    override fun existsById(id: MemberId): Boolean {
        return jpaRepository.existsById(id)
    }

    override fun findByTeam(team: Team): List<Member> {
        return jpaRepository.findByTeam(team)
    }

    override fun save(member: Member): Member {
        return jpaRepository.save(member)
    }

    override fun deleteById(id: MemberId) {
        return jpaRepository.deleteById(id)
    }
}