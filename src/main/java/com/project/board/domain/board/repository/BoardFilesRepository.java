package com.project.board.domain.board.repository;

import com.project.board.domain.board.domain.BoardFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFilesRepository extends JpaRepository<BoardFiles,Long> {
}
