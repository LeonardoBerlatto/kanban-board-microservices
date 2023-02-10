package io.board.kanban.teams.application.config

import io.board.kanban.teams.adapter.mapper.MemberMapper
import io.board.kanban.teams.domain.repository.MemberRepository
import io.board.kanban.teams.domain.repository.RoleRepository
import io.board.kanban.teams.domain.repository.TeamRepository
import io.board.kanban.teams.domain.service.MemberService
import io.board.kanban.teams.domain.service.RoleService
import io.board.kanban.teams.domain.service.TeamService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TeamsConfiguration {

    @Bean
    fun teamService(repository: TeamRepository): TeamService {
        return TeamService(repository)
    }

    @Bean
    fun roleService(repository: RoleRepository): RoleService {
        return RoleService(repository)
    }

    @Bean
    fun memberService(
        repository: MemberRepository,
        roleService: RoleService,
        teamService: TeamService,
        memberMapper: MemberMapper
    ): MemberService {
        return MemberService(repository, roleService, teamService, memberMapper)
    }
}