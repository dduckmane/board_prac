package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.member.domain.Member;
import com.project.board.global.domainConst.MemberConst;
import com.project.board.global.testInit.BoardTestInit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchByRegionAdapterTest extends BoardTestInit {
    @Autowired
    SearchByRegionAdapter searchByRegionAdapter;

    @Test
    void supports() {
        //given
        int groupId = 1;
        //when
        List<String> regions = Arrays.asList(
                Regions.SEOUL.toString()
                , Regions.INCHEON.toString()
                , Regions.GYEONGGI.toString()
                , Regions.GANG.toString()
                , Regions.GN.toString()
                , Regions.GS.toString()
                , Regions.JN.toString()
                , Regions.JS.toString()
                , Regions.JEJU.toString()
        );
        regions.stream().forEach(region -> {
            //when
            boolean supports = searchByRegionAdapter.supports(region);
            //then
            assertThat(supports).isEqualTo(true);
        });

    }

    @Test
    void handle() {
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
        Page<Board> boards = searchByRegionAdapter.handle(region, null, boardSearchCondition, pageRequest);
        //then
        boards.stream().forEach(board -> {
            org.junit.jupiter.api.Assertions.assertAll(
                    () -> assertThat(board.getAddress().getRepresentativeArea()).isEqualTo(region)
                    , () -> assertThat(board.getTitle()).contains("title")
                    , () -> assertThat(board.getMember().getName()).isEqualTo(MemberConst.NAME)
            );
        });
        assertThat(boards.getTotalElements()).isEqualTo(3l);
    }
}