package com.project.board.domain.board.controller;

import com.project.board.domain.board.controller.adapter.*;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.repository.SearchInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryAdapterHandler {
    private final List<findQueryAdapter> queryAdapters= new ArrayList<>();

    public QueryAdapterHandler(BoardRepository boardRepository, SearchInfoRepository searchInfoRepository) {

        queryAdapters.add(new SearchAllAdapter(boardRepository));
        queryAdapters.add(new SearchByRegionAdapter(boardRepository));
        queryAdapters.add(new SearchByChoiceAdapter(boardRepository));
        queryAdapters.add(new SearchByRecommendAdapter(searchInfoRepository ,boardRepository));
    }

    public Page<Board> service(Object param, Member user, BoardSearchCondition searchCondition, Pageable pageable){
        findQueryAdapter handlerAdapter = getHandlerAdapter(param);

        return handlerAdapter.handle(param, user, searchCondition, pageable);
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
