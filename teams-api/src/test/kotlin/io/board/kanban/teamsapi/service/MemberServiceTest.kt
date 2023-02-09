package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.domain.Member
import io.board.kanban.teamsapi.domain.Role
import io.board.kanban.teamsapi.domain.Team
import io.board.kanban.teamsapi.exception.BadRequestException
import io.board.kanban.teamsapi.exception.NotFoundException
import io.board.kanban.teamsapi.mapper.MemberMapper
import io.board.kanban.teamsapi.repository.MemberRepository
import io.board.kanban.teamsapi.representation.CreateMemberRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.UUID

class MemberServiceTest {
    private val repository = mockk<MemberRepository>()
    private val roleService = mockk<RoleService>()
    private val teamService = mockk<TeamService>()
    private val memberMapper = mockk<MemberMapper>()
    private val service = MemberService(repository, roleService, teamService, memberMapper)

    @Test
    fun `should create member when membership does not exist`() {
        // Arrange
        val team = Team(UUID.randomUUID(), "Team 1", listOf())
        val role = Role(UUID.randomUUID(), "Role 1")
        val member = Member(
            userId = UUID.randomUUID(),
            team = team,
            role = role
        )

        every { repository.existsById(any()) } returns false
        every { repository.save(any()) } returns member
        every { roleService.findById(any()) } returns role
        every { teamService.findById(any()) } returns team
        every { memberMapper.toDomain(any(), any(), any()) } returns member

        // Act
        val result = service.create(
            CreateMemberRequest(
                userId = member.userId,
                teamId = member.team.id,
                roleId = member.role.id
            )
        )

        // Assert
        assertThat(result).isEqualTo(member)
    }

    @Test
    fun `should throw exception when membership already exists`() {
        // Arrange
        val team = Team(UUID.randomUUID(), "Team 1", listOf())
        val role = Role(UUID.randomUUID(), "Role 1")
        val member = Member(
            userId = UUID.randomUUID(),
            team = team,
            role = role
        )

        every { repository.existsById(any()) } returns true
        every { roleService.findById(any()) } returns role
        every { teamService.findById(any()) } returns team
        every { memberMapper.toDomain(any(), any(), any()) } returns member

        // Act
        val exception = assertThrows(BadRequestException::class.java) {
            service.create(
                CreateMemberRequest(
                    userId = member.userId,
                    teamId = member.team.id,
                    roleId = member.role.id
                )
            )
        }

        // Assert witth assertj
        assertThat(exception.message).isEqualTo("Member already exists")
    }

    @Test
    fun `should remove member when membership exists`() {
        // Arrange
        val team = Team(UUID.randomUUID(), "Team BR", listOf())
        val role = Role(UUID.randomUUID(), "QA")
        val member = Member(
            userId = UUID.randomUUID(),
            team = team,
            role = role
        )

        every { repository.deleteById(any()) } returns Unit
        every { teamService.findById(any()) } returns team
        every { repository.existsById(any()) } returns true

        // Act
        service.remove(member.userId, member.team.id)

        // Assert
        verify { repository.deleteById(any()) }
    }

    @Test
    fun `should throw exception when membership does not exist`() {
        // Arrange
        val team = Team(UUID.randomUUID(), "Team BR", listOf())
        val role = Role(UUID.randomUUID(), "QA")
        val member = Member(
            userId = UUID.randomUUID(),
            team = team,
            role = role
        )

        every { teamService.findById(any()) } returns team
        every { repository.existsById(any()) } returns false

        // Act
        val exception = assertThrows(NotFoundException::class.java) {
            service.remove(member.userId, member.team.id)
        }

        // Assert
        assertThat(exception.message).isEqualTo("Member does not exist")
    }
}