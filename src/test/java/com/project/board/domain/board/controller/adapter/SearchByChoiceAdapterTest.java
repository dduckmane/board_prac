package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.boardConst.BoardConst;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.member.repository.MemberRepository;
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
class SearchByChoiceAdapterTest extends BoardTestInit {
    @Autowired
    SearchByChoiceAdapter searchByChoiceAdapter;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void supports() {
        //given
        String param = BoardConst.CHOICE;
        //when
        boolean supports = searchByChoiceAdapter.supports(param);
        //then
        assertThat(supports).isEqualTo(true);
    }

    @Test
    void handle() {
        //given
        boardRepoHelper.boardListInit();

        Member member = memberRepository.findByUsername(MemberConst.USERNAME).orElseThrow();
        memberService.choiceBoard(1L,member);
        memberService.choiceBoard(2L,member);
        memberService.choiceBoard(3L,member);
        memberService.choiceBoard(4L,member);
        memberService.choiceBoard(5L,member);
        memberService.choiceBoard(6L,member);

        BoardSearchCondition boardSearchCondition = BoardSearchCondition.builder()
                .name(MemberConst.NAME)
                .title("title")
                .build();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));

        //when
        Page<Board> boards = searchByChoiceAdapter.handle(null, member, boardSearchCondition, pageRequest);
        //then
        boards.getContent().stream().forEach(board -> {
            assertThat(board.getMember()).isEqualTo(member);
        });
    }

}