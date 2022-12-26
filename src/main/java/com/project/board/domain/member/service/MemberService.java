package com.project.board.domain.member.service;

import com.project.board.domain.board.controller.request.ListParam;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
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
    //찜 기능 구현
    public void choiceBoard(Long boardId, Member member) {
        Member findMember = memberRepository.findByUsername(member.getUsername()).orElseThrow();
        findMember.choiceBoard(boardId);
    }

    //정보를 수집
    public void collectInfo(Member member, ListParam listParam, BoardSearchCondition searchCondition) {
        Map<String, String> info = listParam.getInfo();
        Map<String, String> info1 = searchCondition.getInfo();

        info.putAll(info1);

        info.entrySet().stream()
                .filter(entry -> !entry.getValue().equals("sort"))
                .forEach(entry ->{
                    searchInfoService.addCnt(member,entry.getKey(), entry.getValue());
                 });

    }
}
