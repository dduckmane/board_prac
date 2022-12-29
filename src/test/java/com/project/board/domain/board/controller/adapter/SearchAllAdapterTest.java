package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.repository.BoardRepositoryImpl;
import com.project.board.domain.member.domain.Member;
import com.project.board.global.domainConst.MemberConst;
import com.project.board.global.testInit.BoardTestInit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchAllAdapterTest extends BoardTestInit {

    @Autowired
    SearchAllAdapter searchAllAdapter;

    @Test
    void supports() {
        //given
        int groupId = 1;
        //when
        boolean supports = searchAllAdapter.supports(groupId);
        //then
        assertThat(supports).isEqualTo(true);
    }

    @Test
    void handle() {
        //given
        boardRepoHelper.boardListInit();
        int groupId = Category.KOREAN.getGroupId();
        Member member = memberHelper.createMember();

        BoardSearchCondition boardSearchCondition = BoardSearchCondition.builder()
                .name(MemberConst.NAME)
                .title("title")
                .all(null)
                .price("10000")
                .tag(Tag.MOOD.toString())
                .build();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));
        
        //when
        Page<Board> boards = searchAllAdapter.handle(groupId, member, boardSearchCondition, pageRequest);
        //then
        boards.stream().forEach(board -> {
            org.junit.jupiter.api.Assertions.assertAll(
                    () -> assertThat(board.getGroupId()).isEqualTo(groupId)
                    , () -> assertThat(board.getTitle()).contains("title")
                    , () -> assertThat(board.getPrice()).isLessThanOrEqualTo(10000)
                    , () -> assertThat(board.getTagSum()).contains(Tag.MOOD.toString())
                    , () -> assertThat(board.getMember().getName()).isEqualTo(MemberConst.NAME)
            );
        });
        boards.getContent().stream().forEach(board -> {
            System.out.println("board.getId() = " + board.getId());
        });
        System.out.println("boards.get() = " + boards.get());
        
    }
}