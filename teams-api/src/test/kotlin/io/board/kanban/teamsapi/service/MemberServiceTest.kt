package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.domain.Member
import io.board.kanban.teamsapi.domain.Role
import io.board.kanban.teamsapi.domain.Team
import io.board.kanban.teamsapi.exception.BadRequestException
import io.board.kanban.teamsapi.mapper.MemberMapper
import io.board.kanban.teamsapi.repository.MemberRepository
import io.board.kanban.teamsapi.representation.CreateMemberRequest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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

        // Act
        val result = service.create(
            CreateMemberRequest(
                userId = member.userId,
                teamId = member.team.id,
                roleId = member.role.id
            )
        )

        // Assert
        assertEquals(member, result)
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

        // Assert
        assertEquals("Member already exists", exception.message)
    }

    @Test
    fun `should remove member when membership exists`() {
        // Arrange
        val team = Team(UUID.randomUUID(), "Team B", listOf())
        val role = Role(UUID.randomUUID(), "QA")
        val member = Member(
            userId = UUID.randomUUID(),
            team = team,
            role = role
        )

        every { repository.deleteById(any()) } returns Unit
        every { teamService.findById(any()) } returns team

        // Act
        service.remove(member.userId, member.team.id)

        // Assert
        verify { repository.deleteById(any()) }
    }
}