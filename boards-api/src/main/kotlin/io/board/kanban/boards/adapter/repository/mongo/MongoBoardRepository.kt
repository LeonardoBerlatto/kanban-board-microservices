package io.board.kanban.boards.adapter.repository.mongo

import io.board.kanban.boards.domain.entity.Board
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface MongoBoardRepository: MongoRepository<Board, UUID> {

}
