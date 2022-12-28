package com.project.board.domain.member.domain.searchInfo.searchCnt;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.*;

import static com.project.board.domain.board.domain.boardenum.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryCntTest extends TestInit {
    CategoryCnt categoryCnt = new CategoryCnt();

    @Test
    void support() {
        //given
        String name = SearchInfo.CATEGORY;
        //when
        Boolean support = categoryCnt.support(name);
        //then
        assertThat(support).isEqualTo(true);
    }
    @Test
    @Order(1)
    void addCnt() {
        //given
        String groupId = String.valueOf(KOREAN.getGroupId());
        String groupId_2 = String.valueOf(KOREAN.getGroupId());
        String groupId_1 = String.valueOf(AMERICA.getGroupId());
        String groupId_3 = String.valueOf(CHINA.getGroupId());

        //when
        categoryCnt.addCnt(groupId);
        categoryCnt.addCnt(groupId_1);
        categoryCnt.addCnt(groupId_2);
        categoryCnt.addCnt(groupId_3);

        //then
        assertAll(
            ()-> assertThat(categoryCnt.getCategoryOption1()).isEqualTo(2)
            , ()-> assertThat(categoryCnt.getCategoryOption2()).isEqualTo(1)
            , ()-> assertThat(categoryCnt.getCategoryOption3()).isEqualTo(1)
            , ()-> assertThat(categoryCnt.getCategoryOption4()).isEqualTo(0)
        );

    }
    @Test
    @Order(2)
    void getScore() {
        //given
        Board board = boardHelper.createBoard();
        int groupId = board.getGroupId();
        //when
        int score = categoryCnt.getScore(groupId);
        int score_2 = categoryCnt.getScore(2);
        int score_3 = categoryCnt.getScore(3);
        int score_4 = categoryCnt.getScore(4);
        //then
        assertAll(
                ()-> assertThat(score).isEqualTo(2)
                , ()-> assertThat(score_2).isEqualTo(1)
                , ()-> assertThat(score_3).isEqualTo(1)
                , ()-> assertThat(score_4).isEqualTo(0)
        );
    }
}