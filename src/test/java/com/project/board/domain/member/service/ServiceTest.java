package com.project.board.domain.member.service;

import com.project.board.domain.board.boardConst.BoardConst;
import com.project.board.domain.board.controller.request.ListParam;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.repository.SearchInfoRepository;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ServiceTest extends TestInit {
    @Autowired
    MemberService memberService;
    @Autowired
    SearchInfoRepository searchInfoRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void collectInfo() {
        //given
        Member member = memberHelper.createMember();
        SearchInfo searchInfo1 = new SearchInfo(member);
        searchInfoRepository.save(searchInfo1);
        ListParam listParam = new ListParam(null, null);
        //category 별 조회
        ListParam listParam1 = new ListParam(1, null);
        //지역 별 조회
        ListParam listParam2 = new ListParam(null, Regions.SEOUL.toString());
        //추천 페이지 조회
        ListParam listParam3 = new ListParam(null, BoardConst.RECOMMEND);
        //찜 페이지 조회
        ListParam listParam4 = new ListParam(null, BoardConst.CHOICE);
        //필터로 조회 tag 로 조회
        BoardSearchCondition boardSearchCondition1 = BoardSearchCondition.builder()
                .tag(Tag.MOOD.toString())
                .build();
        // 필터로 조회 가격으로 조회
        BoardSearchCondition boardSearchCondition2 = BoardSearchCondition.builder()
                .price("10000")
                .build();
        BoardSearchCondition boardSearchCondition3 = BoardSearchCondition.builder()
                .name("name")
                .build();
        BoardSearchCondition boardSearchCondition4 = BoardSearchCondition.builder()
                .title("돈까스")
                .build();
        BoardSearchCondition boardSearchCondition5 = BoardSearchCondition.builder()
                .all("돈까스")
                .build();
        //when
        memberService.collectInfo(member,listParam1,boardSearchCondition1);
        memberService.collectInfo(member,listParam2,boardSearchCondition1);
        memberService.collectInfo(member,listParam3,boardSearchCondition1);
        memberService.collectInfo(member,listParam4,boardSearchCondition1);
        memberService.collectInfo(member,listParam,boardSearchCondition2);
        memberService.collectInfo(member,listParam,boardSearchCondition3);
        memberService.collectInfo(member,listParam,boardSearchCondition4);
        memberService.collectInfo(member,listParam,boardSearchCondition5);
        //then
        SearchInfo searchInfo = searchInfoRepository.findByMember(member).orElseThrow();

        assertAll(
                () -> assertThat(searchInfo.getCategory().getCategoryOption1()).isEqualTo(1)
                , () -> assertThat(searchInfo.getRegionsCnt().getRegionsOption1()).isEqualTo(1)
                , () -> assertThat(searchInfo.getTagCnt().getTagOption1()).isEqualTo(4)
                , () -> assertThat(searchInfo.getPriceCnt().getPriceOption1()).isEqualTo(1)
                , () -> assertThat(searchInfo.getNameInfoAdd().getOrderMap().get("돈까스")).isEqualTo(2)
        );
    }

    @Test
    void withdrawal() {
        //given
        Member member = memberHelper.createMember();
        //when
        memberService.withdrawal(member);
        //then
        assertThrows(NoSuchElementException.class,() ->
                memberRepository.findByUsername(member.getUsername()).orElseThrow());
    }
}