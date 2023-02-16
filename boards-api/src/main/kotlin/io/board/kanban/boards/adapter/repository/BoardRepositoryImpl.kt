package io.board.kanban.boards.adapter.repository

import io.board.kanban.boards.adapter.repository.mongo.MongoBoardRepository
import io.board.kanban.boards.domain.entity.Board
import io.board.kanban.boards.domain.repository.BoardRepository
import org.springframework.stereotype.Repository

@Repository
class BoardRepositoryImpl(private val mongoRepository: MongoBoardRepository) : BoardRepository {

    override fun save(board: Board): Board {
        return mongoRepository.save(board)
    }
}