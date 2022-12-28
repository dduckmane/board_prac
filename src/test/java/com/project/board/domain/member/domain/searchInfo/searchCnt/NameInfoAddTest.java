package com.project.board.domain.member.domain.searchInfo.searchCnt;

import com.project.board.domain.board.domain.Board;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NameInfoAddTest extends TestInit {

    NameInfoAdd nameInfoAdd = new NameInfoAdd();
    @Test
    void support() {
        //given
        String title = "title";
        String all = "all";
        //when
        Boolean support = nameInfoAdd.support(title);
        Boolean support1 = nameInfoAdd.support(all);
        //then
        assertThat(support).isEqualTo(true);
        assertThat(support1).isEqualTo(true);
    }
    @Test
    @Order(1)
    void addCnt() {
        //given
        String title = "동대문 떡볶이";
        String title2 = "동대문 돈까스";
        String title3 = "먹기 좋은 동대문 파스타";

        //when
        nameInfoAdd.addCnt(title);
        nameInfoAdd.addCnt(title2);
        nameInfoAdd.addCnt(title3);

        //then
        assertAll(
            ()-> assertThat(nameInfoAdd.getOrderMap().get("동대문")).isEqualTo(3)
            , ()-> assertThat(nameInfoAdd.getOrderMap().get("떡볶이")).isEqualTo(1)
            , ()-> assertThat(nameInfoAdd.getOrderMap().get("돈까스")).isEqualTo(1)
            , ()-> assertThat(nameInfoAdd.getOrderMap().get("파스타")).isEqualTo(1)
        );

    }
    @Test
    @Order(2)
    void getScore() {
        //given
        Board board = boardHelper.createBoard();
        String title = board.getTitle();

        String title1 = "홍대 파스타 맛집";
        String title2 = "신림 떡볶이";
        String title3 = "돈까스와 파스타의 만남";
        //when
        int score = nameInfoAdd.getScore(title);
        int score1 = nameInfoAdd.getScore(title1);
        int score2 = nameInfoAdd.getScore(title2);
        int score3 = nameInfoAdd.getScore(title3);
        //then
        assertAll(
                ()-> assertThat(score).isEqualTo(4)
                ,()-> assertThat(score1).isEqualTo(1)
                ,()-> assertThat(score2).isEqualTo(1)
                ,()-> assertThat(score3).isEqualTo(2)
        );
    }
}