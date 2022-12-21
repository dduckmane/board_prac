package com.project.board.domain.board.controller;

import com.project.board.domain.board.controller.adapter.*;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.board.search.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryAdapterHandler {
    private final BoardRepositoryImpl boardRepository;
    private final List<findQueryAdapter> queryAdapters= new ArrayList<>();

    public QueryAdapterHandler(BoardRepositoryImpl boardRepository) {
        this.boardRepository = boardRepository;

        queryAdapters.add(new SearchAllAdapter(boardRepository));
        queryAdapters.add(new SearchByRegionAdapter(boardRepository));
        queryAdapters.add(new SearchByChoiceAdapter(boardRepository));
        queryAdapters.add(new SearchByRecommendAdapter(boardRepository));
    }

    public Page<Board> service(Object param, BoardSearchCondition searchCondition, Pageable pageable){
        findQueryAdapter handlerAdapter = getHandlerAdapter(param);

        return handlerAdapter.handle(param, searchCondition, pageable);
    }
    private findQueryAdapter getHandlerAdapter(Object param){
        for (findQueryAdapter queryAdapter : queryAdapters) {
            if(queryAdapter.supports(param)){
                return queryAdapter;
            }
        }
        throw new IllegalArgumentException("No Search Handler");
    }
}
