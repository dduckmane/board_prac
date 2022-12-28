package com.project.board.domain.member.domain;

import com.project.board.domain.board.domain.Board;
import com.project.board.global.helper.BoardHelper;
import com.project.board.global.helper.MemberHelper;
import com.project.board.global.testInit.MemberTestInit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class MemberTest extends MemberTestInit {

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