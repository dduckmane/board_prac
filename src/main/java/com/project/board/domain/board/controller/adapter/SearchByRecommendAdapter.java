package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.controller.adapter.dto.RecommendListDto;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.member.repository.SearchInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.board.domain.board.boardConst.BoardConst.*;

@Component
@RequiredArgsConstructor
/**
 * 추천 페이지 관련 adapter
 **/
public class SearchByRecommendAdapter implements findQueryAdapter{
    private final SearchInfoRepository searchInfoRepository;

    private final BoardRepository boardRepository;
    //각 board 와 그에 맞는 회원 별 board 에 대한 점수를 list 에 담아 놓는다.
    private List<RecommendListDto> recommendListDtos =new ArrayList<>();

    @Override
    public boolean supports(Object param) {
        if(!(param instanceof String)) return false;

        String changeParam = (String) param;
        return changeParam.equals(RECOMMEND);
    }

    @Override
    public Page<Board> handle(Object param, Member user, BoardSearchCondition searchCondition, Pageable pageable) {
        // 회원의 검색 정보를 가져온다.
        SearchInfo searchInfo = searchInfoRepository.findSearchInfoByMember(user).orElseThrow();
        //하기전 list 를 비움
        recommendListDtos.clear();

        Page<Board> boards = boardRepository.searchAll(searchCondition, pageable);
        List<Board> content = boards.getContent();

        content.stream().forEach(board -> {
            //각 board 의 점수를 환산
            int totalScore = searchInfo.getTotalScore(board);

            //환산한 board 의 점수와 board 를 list 에 담음
            recommendListDtos.add(new RecommendListDto(totalScore,board));
        });
        // 점수 바탕으로 정렬
        Collections.sort(recommendListDtos);

        //새로운 page 객체를 만듦
        List<Board> result = recommendListDtos
                .stream()
                .map(RecommendListDto::getBoard)
                .collect(Collectors.toList());

        return new PageImpl<>(result,pageable,boards.getTotalElements());
    }
}
