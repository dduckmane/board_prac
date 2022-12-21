package com.project.board.domain.reply.repository;

import com.project.board.domain.reply.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyRepositoryCustom {
    Page<Reply>searchAll(Long boardId, Pageable pageable);
    Long getTotalCount();
}
