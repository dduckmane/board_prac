package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.boardConst.BoardConst;
import com.project.board.domain.board.controller.BoardController;
import com.project.board.domain.board.controller.request.ListParam;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.repository.SearchInfoRepository;
import com.project.board.domain.member.service.MemberService;
import com.project.board.global.domainConst.MemberConst;
import com.project.board.global.testInit.BoardTestInit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchByRecommendAdapterTest extends BoardTestInit {

    @Autowired
    SearchByRecommendAdapter searchByRecommendAdapter;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    SearchInfoRepository searchInfoRepository;

    @Test
    void supports() {
        //given
        String param = BoardConst.RECOMMEND;
        //when
        boolean supports = searchByRecommendAdapter.supports(param);
        //then
        assertThat(supports).isEqualTo(true);
    }

    @Test
    void handle() {
        //given
        boardRepoHelper.boardListInit();
        Member member = memberRepository.findByUsername(MemberConst.USERNAME).orElseThrow();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));
        // 정보 수집
        SearchInfo searchInfo1 = new SearchInfo(member);
        searchInfoRepository.save(searchInfo1);
        
        SearchInfo searchInfo = searchInfoRepository.findSearchInfoByMember(member).orElseThrow();
        
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
        memberService.collectInfo(member,listParam1,boardSearchCondition1);
        memberService.collectInfo(member,listParam2,boardSearchCondition1);
        memberService.collectInfo(member,listParam3,boardSearchCondition1);
        memberService.collectInfo(member,listParam4,boardSearchCondition1);
        memberService.collectInfo(member,listParam,boardSearchCondition2);
        memberService.collectInfo(member,listParam,boardSearchCondition3);
        memberService.collectInfo(member,listParam,boardSearchCondition4);
        memberService.collectInfo(member,listParam,boardSearchCondition5);
        //현재 점수
        // 한식 - 1점, 서울 - 1점, 분위기 태그 - 4점, 가격 10000이하 1점, 돈까스 2점
        
        //when
        Page<Board> boards = searchByRecommendAdapter.handle(listParam, member, BoardSearchCondition.builder().build(), pageRequest);
        
        boards.getContent().stream().forEach(board -> {
            System.out.println("board.getId() = " + board.getId());
            System.out.println("board.getId() = " + board.getPrice());
            System.out.println("board.getId() = " + board.getTagSum());
            System.out.println("board.getId() = " + board.getAddress().getRepresentativeArea());
            System.out.println("board.getId() = " + board.getTitle());
            System.out.println("searchInfo.getTotalScore(board) = " + searchInfo.getTotalScore(board));
        });
    }
}