package com.project.board.domain.member.domain.searchInfo.searchCnt;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.*;

import static com.project.board.global.domainConst.BoardConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagCntTest extends TestInit {

    TagCnt tagCnt = new TagCnt();
    @Test
    void support() {
        //given
        String name = SearchInfo.TAG;
        //when
        Boolean support = tagCnt.support(name);
        //then
        assertThat(support).isEqualTo(true);
    }
    @Test
    @Order(1)
    void addCnt() {
        //given
        String tags = TAG_PLAY+","+TAG_MOOD;
        String tags_1 = TAG_PLAY+","+TAG_PRICE;

        //when
        tagCnt.addCnt(tags);
        tagCnt.addCnt(tags_1);

        //then
        assertAll(
            ()-> assertThat(tagCnt.getTagOption1()).isEqualTo(1)
            , ()-> assertThat(tagCnt.getTagOption2()).isEqualTo(1)
            , ()-> assertThat(tagCnt.getTagOption3()).isEqualTo(0)
            , ()-> assertThat(tagCnt.getTagOption4()).isEqualTo(2)
        );

    }
    @Test
    @Order(2)
    void getScore() {
        //given
        Board board = boardHelper.createBoard();
        String tagSum = board.getTagSum();
        //when
        int score = tagCnt.getScore(tagSum);
        int score_2 = tagCnt.getScore(","+TAG_PRICE+","+TAG_MOOD);
        int score_3 = tagCnt.getScore(","+TAG_RESERVATION+","+TAG_MOOD+TAG_PRICE);
        int score_4 = tagCnt.getScore(","+TAG_MOOD+","+TAG_RESERVATION);
        //then
        assertAll(
                ()-> assertThat(score).isEqualTo(1)
                , ()-> assertThat(score_2).isEqualTo(2)
                , ()-> assertThat(score_3).isEqualTo(2)
                , ()-> assertThat(score_4).isEqualTo(1)
        );
    }
}