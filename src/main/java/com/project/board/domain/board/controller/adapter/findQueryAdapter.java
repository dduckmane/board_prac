package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.search.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface findQueryAdapter {
    boolean supports(Object param);

    Page<Board> handle(Object param, BoardSearchCondition searchCondition, Pageable pageable);
}
