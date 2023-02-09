package io.board.kanban.teamsapi.config

import io.board.kanban.teamsapi.mapper.MemberMapper
import io.board.kanban.teamsapi.repository.MemberRepository
import io.board.kanban.teamsapi.repository.RoleRepository
import io.board.kanban.teamsapi.repository.TeamRepository
import io.board.kanban.teamsapi.service.MemberService
import io.board.kanban.teamsapi.service.RoleService
import io.board.kanban.teamsapi.service.TeamService
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
        roleService: RoleService,
        teamService: TeamService,
        repository: MemberRepository,
        memberMapper: MemberMapper
    ): MemberService {
        return MemberService(roleService, teamService, repository, memberMapper)
    }
}