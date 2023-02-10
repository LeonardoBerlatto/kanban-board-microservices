package io.board.kanban.teams.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "members")
@IdClass(MemberId::class)
class Member(
    @Id
    val userId: UUID,
    @Id
    @ManyToOne
    @JoinColumn(name = "team_id")
    val team: Team,
    @ManyToOne
    @JoinColumn(name = "role_id")
    var role: Role
)
