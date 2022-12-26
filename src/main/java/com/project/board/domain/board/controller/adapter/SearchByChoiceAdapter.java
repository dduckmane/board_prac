package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.project.board.domain.board.boardConst.BoardConst.*;

@RequiredArgsConstructor
public class SearchByChoiceAdapter implements findQueryAdapter{

    private final BoardRepositoryImpl boardRepository;

    @Override
    public boolean supports(Object param) {
        if(!(param instanceof String)) return false;

        String changeParam = (String) param;
        return changeParam.equals(CHOICE);
    }

    @Override
    public Page<Board> handle(Object param, BoardSearchCondition searchCondition, Pageable pageable) {

        return boardRepository.searchByChoice(searchCondition,pageable);
    }
}
