package io.board.kanban.teamsapi.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "teams")
data class Team(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String,
    @OneToMany(mappedBy = "team")
    val members: List<Member>
): Serializable
