package com.project.board.domain.member.domain.searchInfo.searchCnt;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PriceCntTest extends TestInit {
    
    PriceCnt priceCnt=new PriceCnt();
    @Test
    void support() {
        //given
        String name = SearchInfo.PRICE;
        //when
        Boolean support = priceCnt.support(name);
        //then
        assertThat(support).isEqualTo(true);
    }
    @Test
    @Order(1)
    void addCnt() {
        //given
        String price_1 = String.valueOf(10000);
        String price_2 = String.valueOf(20000);
        String price_3 = String.valueOf(30000);
        String price_4 = String.valueOf(40000);

        //when
        priceCnt.addCnt(price_1);
        priceCnt.addCnt(price_1);
        priceCnt.addCnt(price_2);
        priceCnt.addCnt(price_3);

        //then
        assertAll(
            ()-> assertThat(priceCnt.getPriceOption1()).isEqualTo(2)
            , ()-> assertThat(priceCnt.getPriceOption2()).isEqualTo(1)
            , ()-> assertThat(priceCnt.getPriceOption3()).isEqualTo(1)
            , ()-> assertThat(priceCnt.getPriceOption4()).isEqualTo(0)
        );

    }
    @Test
    @Order(2)
    void getScore() {
        //given
        Board board = boardHelper.createBoard();
        int price = board.getPrice();
        //when
        int score = priceCnt.getScore(price);
        int score_2 = priceCnt.getScore(19000);
        int score_3 = priceCnt.getScore(29000);
        int score_4 = priceCnt.getScore(39000);
        //then
        assertAll(
                ()-> assertThat(score).isEqualTo(2)
                , ()-> assertThat(score_2).isEqualTo(1)
                , ()-> assertThat(score_3).isEqualTo(1)
                , ()-> assertThat(score_4).isEqualTo(0)
        );
    }
}