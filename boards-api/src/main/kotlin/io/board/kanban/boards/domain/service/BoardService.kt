package io.board.kanban.boards.domain.service

import io.board.kanban.boards.domain.entity.Board
import io.board.kanban.boards.domain.exception.NotFoundException
import io.board.kanban.boards.domain.repository.BoardRepository
import java.util.UUID

class BoardService(
    private val repository: BoardRepository,
    private val teamService: TeamService,
    private val issueService: IssueService
) {

    fun findById(id: UUID): Board =
        repository
            .findById(id)
            .orElseThrow { NotFoundException("Board not found") }


    fun create(board: Board): Board {
        if (!teamService.existById(board.teamId)) {
            throw NotFoundException("Team with id ${board.teamId} not found")
        }

        validateIssues(board.issues)

        return repository.save(board)
    }

    private fun validateIssues(issues: List<UUID>) =
        issues.firstOrNull { !issueService.existById(it) }?.let {
            throw NotFoundException("Issue with id $it not found")
        }

    fun removeIssueFromBoards(issueId: UUID?) {
        if (issueId == null) {
            return
        }

        repository.findAll()
            .forEach(removeIssueIfApplicable(issueId))
    }

    private fun removeIssueIfApplicable(issueId: UUID) = forEach@{ board: Board ->
        if (!board.issues.contains(issueId)) {
            return@forEach
        }

        board.issues = board.issues.filter { it != issueId }
        repository.save(board)
    }

}
