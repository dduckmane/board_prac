package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.member.domain.Member;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface findQueryAdapter {
    boolean supports(Object param);

    Page<Board> handle(Object param, Member user, BoardSearchCondition searchCondition, Pageable pageable);
}
