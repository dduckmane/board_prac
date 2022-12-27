package com.project.board.domain.member.service;

import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.member.repository.SearchInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchInfoService {

    private final SearchInfoRepository searchInfoRepository;

    public void addCnt(Member member, String type, String param){
        SearchInfo searchInfo = searchInfoRepository
                .findByMember(member)
                .orElseThrow();

        searchInfo.addCnt(member,type,param);

    }
}
