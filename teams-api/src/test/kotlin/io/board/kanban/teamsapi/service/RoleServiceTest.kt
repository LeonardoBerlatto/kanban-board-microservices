package io.board.kanban.teamsapi.service

import io.board.kanban.teamsapi.exception.NotFoundException
import io.board.kanban.teamsapi.domain.Role
import io.board.kanban.teamsapi.repository.RoleRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.UUID

class RoleServiceTest {

    private val repository = mockk<RoleRepository>()

    private val service = RoleService(repository)

    @Test
    fun `should return all roles`() {
        // Arrange
        every { repository.findAll() } returns listOf(
            Role(name = "Admin"),
            Role(name = "User")
        )

        // Act
        val roles = service.getAll()

        // Assert
        assertThat(roles).hasSize(2)
    }

    @Test
    fun `should find a role by id when role exists`() {
        // Arrange
        every { repository.findById(any()) } returns Role(name = "Admin")

        // Act
        val role = service.findById(UUID.randomUUID())

        // Assert
        assertThat(role.name).isEqualTo("Admin")
    }

    @Test
    fun `should throw exception when role does not exist`() {
        // Arrange
        every { repository.findById(any()) } returns null

        // Act
        val exception = assertThrows(NotFoundException::class.java) {
            service.findById(UUID.randomUUID())
        }

        // Assert
        assertThat(exception.message).isEqualTo("Role not found")
    }
}