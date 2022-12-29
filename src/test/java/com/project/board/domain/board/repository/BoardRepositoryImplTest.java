package com.project.board.domain.board.repository;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.service.MemberService;
import com.project.board.global.domainConst.MemberConst;
import com.project.board.global.testInit.BoardTestInit;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.awt.print.Pageable;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class BoardRepositoryImplTest extends BoardTestInit {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Test
    void searchAllCondition() {
        //given
        boardRepoHelper.boardListInit();
        int groupId = Category.KOREAN.getGroupId();

        BoardSearchCondition boardSearchCondition = BoardSearchCondition.builder()
                .name(MemberConst.NAME)
                .title("title")
                .all(null)
                .price("10000")
                .tag(Tag.MOOD.toString())
                .build();

        PageRequest pageRequest = PageRequest.of(0, 10,Sort.by("id"));
        //when
        Page<Board> boards = boardRepository.searchAllCondition(groupId, boardSearchCondition, pageRequest);
        //then
        boards.stream().forEach(board -> {
            Assertions.assertAll(
                    () -> assertThat(board.getGroupId()).isEqualTo(groupId)
                    , () -> assertThat(board.getTitle()).contains("title")
                    , () -> assertThat(board.getPrice()).isLessThanOrEqualTo(10000)
                    , () -> assertThat(board.getTagSum()).contains(Tag.MOOD.toString())
                    , () -> assertThat(board.getMember().getName()).isEqualTo(MemberConst.NAME)
            );
        });

    }

    @Test
    void searchByRegions() {
        //given
        boardRepoHelper.boardListInit();
        String region = Regions.SEOUL.toString();

        BoardSearchCondition boardSearchCondition = BoardSearchCondition.builder()
                .name(MemberConst.NAME)
                .title("title")
                .all(null)
                .build();

        PageRequest pageRequest = PageRequest.of(0, 10,Sort.by("id"));
        //when
        Page<Board> boards = boardRepository.searchByRegions(region, boardSearchCondition, pageRequest);
        //then
        boards.getContent().stream().forEach(board -> {
            Assertions.assertAll(
                    () -> assertThat(board.getAddress().getRepresentativeArea()).isEqualTo(region)
                    , () -> assertThat(board.getTitle()).contains("title")
                    , () -> assertThat(board.getMember().getName()).isEqualTo(MemberConst.NAME)
            );
        });

    }

    @Test
    void searchAll() {
        //given
        boardRepoHelper.boardListInit();

        BoardSearchCondition boardSearchCondition = BoardSearchCondition.builder()
                .name(MemberConst.NAME)
                .title("title")
                .all(null)
                .price("10000")
                .tag(Tag.MOOD.toString())
                .build();

        PageRequest pageRequest = PageRequest.of(0, 10,Sort.by("id"));
        //when
        Page<Board> boards = boardRepository.searchAll(boardSearchCondition, pageRequest);
        //then
        boards.stream().forEach(board -> {
            Assertions.assertAll(
                    () -> assertThat(board.getTitle()).contains("title")
                    , () -> assertThat(board.getPrice()).isLessThanOrEqualTo(10000)
                    , () -> assertThat(board.getTagSum()).contains(Tag.MOOD.toString())
                    , () -> assertThat(board.getMember().getName()).isEqualTo(MemberConst.NAME)
            );
        });
    }

    @Test
    void searchByChoice() {
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
                .all(null)
                .build();

        PageRequest pageRequest = PageRequest.of(0, 10,Sort.by("id"));
        //when
        Page<Board> boards = boardRepository.searchByChoice(member, boardSearchCondition, pageRequest);
        //then
        boards.stream().forEach(board -> {
            Assertions.assertAll(
                    () -> assertThat(board.getTitle()).contains("title")
                    , () -> assertThat(board.getMember().getName()).isEqualTo(MemberConst.NAME)
            );
        });
        assertThat(boards.getTotalElements()).isEqualTo(6L);
    }

    @Test
    void searchBestInfo() {
        //given
        boardRepoHelper.boardListInit();

        PageRequest pageRequest = PageRequest.of(0, 10,Sort.by("id"));
        //when
        Page<Board> boards = boardRepository.searchBestInfo(pageRequest);
        //then
        assertThat(boards.getTotalElements()).isEqualTo(6L);
    }
}