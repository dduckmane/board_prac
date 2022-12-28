package com.project.board.domain.member.service;

import com.project.board.domain.board.controller.request.ListParam;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.service.BoardService;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SearchInfoService searchInfoService;
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    //찜 기능 구현
    public void choiceBoard(Long boardId, Member member) {
        memberRepository
                .findByUsername(member.getUsername())
                .orElseThrow()
                .choiceBoard(boardId);
    }

    //정보를 수집
    public void collectInfo(
            Member member
            , ListParam listParam
            , BoardSearchCondition searchCondition
    ) {
        Map<String, String> info = listParam.getInfo();
        Map<String, String> info1 = searchCondition.getInfo();

        info.putAll(info1);

        info.entrySet().stream()
                .filter(entry -> !entry.getValue().equals("sort"))
                .forEach(entry ->{
                    searchInfoService.addCnt(member,entry.getKey(), entry.getValue());
                 });

    }

    public void withdrawal(Member member){
        boardRepository
                .findBoardByMember(member)
                .stream()
                .forEach(board -> { boardService.delete(board.getId());});

        memberRepository.delete(member);
    }
}
