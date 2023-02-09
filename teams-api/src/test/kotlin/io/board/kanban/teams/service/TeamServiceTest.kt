package io.board.kanban.teams.service

import io.board.kanban.teams.exception.NotFoundException
import io.board.kanban.teams.domain.Team
import io.board.kanban.teams.exception.BadRequestException
import io.board.kanban.teams.repository.TeamRepository
import io.board.kanban.teams.representation.CreateTeamRequest
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.UUID

class TeamServiceTest {

    private val repository = mockk<TeamRepository>()
    private val service = TeamService(repository)

    @Test
    fun `should find team by id`() {
        // Arrange
        every { repository.findById(any()) } returns Team(
            name = "Team 1",
            members = emptyList()
        )

        // Act
        val team = service.findById(UUID.randomUUID())

        // Assert
        assertThat(team.name).isEqualTo("Team 1")
    }

    @Test
    fun `should throw exception when team does not exist`() {
        // Arrange
        every { repository.findById(any()) } returns null

        // Act
        val exception = assertThrows(NotFoundException::class.java) {
            service.findById(UUID.randomUUID())
        }

        // Assert
        assertThat(exception.message).isEqualTo("Team not found")
    }

    @Test
    fun `should create a team when name is not blank`() {
        // Arrange
        every { repository.save(any()) } returns Team(
            name = "Team 1",
            members = emptyList()
        )

        val request = CreateTeamRequest(name = "Team 1")

        // Act
        val team = service.create(request)

        // Assert
        assertThat(team.name).isEqualTo(request.name)
    }

    @Test
    fun `should throw exception when name is blank`() {
        // Arrange
        val request = CreateTeamRequest(name = "")

        // Act
        val exception = assertThrows(BadRequestException::class.java) {
            service.create(request)
        }

        // Assert
        assertThat(exception.message).isEqualTo("Team name cannot be blank")
    }

    @Test
    fun `should find team by name`() {
        val name = "Team"
        // Arrange
        every { repository.findByName(name, any()) } returns PageImpl(
            listOf(
                Team(name = "Team 1", members = emptyList()),
                Team(name = "Team 2", members = emptyList())
            )
        )

        // Act
        val teams = service.findTeamByName(name, Pageable.unpaged())

        // Assert
        assertThat(teams).hasSize(2)
    }
}