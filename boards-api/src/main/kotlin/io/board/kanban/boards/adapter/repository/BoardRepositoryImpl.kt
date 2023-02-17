package io.board.kanban.boards.adapter.repository

import io.board.kanban.boards.adapter.repository.mongo.MongoBoardRepository
import io.board.kanban.boards.domain.entity.Board
import io.board.kanban.boards.domain.repository.BoardRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
class BoardRepositoryImpl(private val mongoRepository: MongoBoardRepository) : BoardRepository {

    @Cacheable("boards", key = "#methodName - #id", unless = "#result == null")
    override fun findById(id: UUID): Optional<Board> {
        return mongoRepository.findById(id)
    }

    @CacheEvict("boards", allEntries = true)
    override fun save(board: Board): Board {
        return mongoRepository.save(board)
    }
}