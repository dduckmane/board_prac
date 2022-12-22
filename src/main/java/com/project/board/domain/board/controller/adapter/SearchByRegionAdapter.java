package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.board.search.BoardSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class SearchByRegionAdapter implements findQueryAdapter{

    private final BoardRepositoryImpl boardRepository;

    @Override
    public boolean supports(Object param) {
        if(!(param instanceof String)) return false;
        String regions="SEOUL GYEONGGI INCHEON GANG JN JS GS GN JEJU";
        String region = (String) param;

        boolean contains = regions.contains(region);
        return contains;
    }

    @Override
    public Page<Board> handle(Object param, BoardSearchCondition searchCondition, Pageable pageable) {
        String region = (String) param;

        return boardRepository.searchByRegions(region,searchCondition,pageable);
    }
}
