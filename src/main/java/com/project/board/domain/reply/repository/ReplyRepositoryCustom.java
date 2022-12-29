package com.project.board.domain.reply.repository;

import com.project.board.domain.reply.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

public interface ReplyRepositoryCustom {
    @EntityGraph(attributePaths = {"member"})
    Page<Reply>searchAll(Long boardId, Pageable pageable);
}
