package com.project.board.domain.member.domain;

import com.project.board.domain.board.domain.Board;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest extends TestInit {

    @Test
    void choiceBoard() {
        //given
        Board board = boardHelper.createBoard();
        Member member = memberHelper.createMember();
        //when
        member.choiceBoard(board.getId());
        //then
        member.getChoiceBoard().stream().forEach(boardId->{
            assertThat(boardId).isEqualTo(board.getId());
        });
        //when
        member.choiceBoard(board.getId());

        int size = member.getChoiceBoard().size();
        //then
        assertThat(size).isEqualTo(0);
    }
}