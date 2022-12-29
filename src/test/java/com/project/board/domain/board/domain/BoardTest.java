package com.project.board.domain.board.domain;

import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.project.board.domain.board.domain.boardenum.Tag.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest extends TestInit {

    @Test
    void update() {
        //given
        Board board = boardHelper.createBoard();

        String updateTitle = "title";
        String updateContent = "content";
        Address updateAddress = new Address("updateArea", "updateDetailArea");
        int updatePrice = 20000;
        String updateTagSum = MOOD.toString() + PLAY.toString();

        //when
        board.update(
                updateTitle
                ,updateContent
                ,null
                ,updateAddress
                ,updatePrice
                ,updateTagSum);

        //then
        assertAll(
            () -> assertThat(board.getTitle()).isEqualTo(updateTitle)
            , () -> assertThat(board.getContent()).isEqualTo(updateContent)
            , () -> assertThat(board.getAddress()).usingRecursiveComparison().isEqualTo(updateAddress)
            , () -> assertThat(board.getPrice()).isEqualTo(updatePrice)
            , () -> assertThat(board.getTagSum()).isEqualTo(updateTagSum)
        );
    }

    @Test
    void substringTitle() {
        //given
        Board board = boardHelper.createBoard();
        //when
        String subTitle = board.substringTitle();
        //then
        assertThat(subTitle.length()).isLessThan(12);
    }
}