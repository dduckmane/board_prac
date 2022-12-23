package com.project.board.domain.board.repository;

import com.project.board.domain.board.domain.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long>,BoardRepositoryCustom {

    @EntityGraph(attributePaths = {"member"})
    Optional<Board> findMemberById(Long boardId);

}

