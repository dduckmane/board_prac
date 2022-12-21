package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.board.search.BoardSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class SearchAllAdapter implements findQueryAdapter{

    private final BoardRepositoryImpl boardRepository;

    @Override
    public boolean supports(Object param) {
        return param instanceof Integer;
    }

    @Override
    public Page<Board> handle(Object param, BoardSearchCondition searchCondition, Pageable pageable) {
        int groupId = (int) param;

        return boardRepository.searchAllCondition(groupId,searchCondition,pageable);
    }
}
