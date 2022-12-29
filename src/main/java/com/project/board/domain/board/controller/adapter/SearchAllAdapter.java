package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//한식, 양식, 중식, 일식 페이지 관련 adapter
public class SearchAllAdapter implements findQueryAdapter{

    private final BoardRepository boardRepository;

    @Override
    public boolean supports(Object param) {
        return param instanceof Integer;
    }

    @Override
    public Page<Board> handle(Object param, Member user, BoardSearchCondition searchCondition, Pageable pageable) {
        int groupId = (int) param;

        return boardRepository.searchAllCondition(groupId,searchCondition,pageable);
    }
}
