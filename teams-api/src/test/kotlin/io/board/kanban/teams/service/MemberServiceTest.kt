package io.board.kanban.teams.service

import io.board.kanban.teams.domain.entity.Member
import io.board.kanban.teams.domain.entity.Role
import io.board.kanban.teams.domain.entity.Team
import io.board.kanban.teams.domain.exception.BadRequestException
import io.board.kanban.teams.domain.exception.NotFoundException
import io.board.kanban.teams.adapter.mapper.MemberMapper
import io.board.kanban.teams.domain.repository.MemberRepository
import io.board.kanban.teams.adapter.representation.MemberRequest
import io.board.kanban.teams.domain.service.MemberService
import io.board.kanban.teams.domain.service.RoleService
import io.board.kanban.teams.domain.service.TeamService
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
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

    private val memberSlot = slot<Member>()

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
            MemberRequest(
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
                MemberRequest(
                    userId = member.userId,
                    teamId = member.team.id,
                    roleId = member.role.id
                )
            )
        }

        // Assert
        assertThat(exception.message).isEqualTo("Member already exists")
    }

    @Test
    fun `should update member when member exists`() {
        // Arrange
        val team = Team(UUID.randomUUID(), "Team 1", listOf())
        val role = Role(UUID.randomUUID(), "Role 1")
        val existingMember = Member(
            userId = UUID.randomUUID(),
            team = team,
            role = role
        )

        val request = MemberRequest(
            userId = existingMember.userId,
            teamId = existingMember.team.id,
            roleId = UUID.randomUUID()
        )

        every { teamService.findById(existingMember.team.id) } returns team
        every { repository.findById(any()) } returns existingMember
        every { roleService.findById(request.roleId) } returns role
        every { repository.save(capture(memberSlot)) } returns existingMember

        // Act
        val result = service.update(request)

        // Assert
        assertThat(result.role).isEqualTo(memberSlot.captured.role)
    }

    @Test
    fun `should throw exception when member does not exist`() {
        // Arrange
        val team = Team(UUID.randomUUID(), "Team 1", listOf())
        val role = Role(UUID.randomUUID(), "Role 1")
        val existingMember = Member(
            userId = UUID.randomUUID(),
            team = team,
            role = role
        )

        val request = MemberRequest(
            userId = existingMember.userId,
            teamId = existingMember.team.id,
            roleId = UUID.randomUUID()
        )

        every { teamService.findById(existingMember.team.id) } returns team
        every { repository.findById(any()) } returns null
        every { roleService.findById(request.roleId) } returns role

        // Act
        val exception = assertThrows(NotFoundException::class.java) {
            service.update(request)
        }

        // Assert
        assertThat(exception.message).isEqualTo("Member does not exist")
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