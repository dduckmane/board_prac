package com.project.board.domain.reply.service;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.reply.domain.Reply;
import com.project.board.domain.reply.repository.ReplyRepository;
import com.project.board.global.domainConst.MemberConst;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ReplyServiceTest extends TestInit {
    @Autowired
    ReplyService replyService;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void save() {
        //given
        Member member = memberHelper.createMember();
        Board board = boardHelper.createBoard();
        String replyText="replyText";
        //when
        Long replyId = replyService.save(board.getId(), member, replyText);
        //then
        Reply reply = replyRepository.findById(replyId).orElseThrow();

        assertAll(
                () -> assertThat(reply.getReplyText()).isEqualTo(replyText)
                , () -> assertThat(reply.getMember()).isEqualTo(member)
                , () -> assertThat(reply.getBoard()).isEqualTo(board)
        );
    }

    @Test
    void update() {
        //given
        Board board = boardHelper.createBoard();
        Member member = memberRepository.findByUsername(MemberConst.USERNAME).orElseThrow();

        Long replyId = replyService.save(
                board.getId()
                , member
                , "replyText"
        );

        String updateReplyText = "update";
        //when
        replyService.update(replyId, member , updateReplyText);
        //then
        Reply reply = replyRepository.findById(replyId).orElseThrow();

        assertThat(reply.getReplyText()).isEqualTo(updateReplyText);
    }

    @Test
    void delete() {
        //given
        Board board = boardHelper.createBoard();
        Member member = memberRepository.findByUsername(MemberConst.USERNAME).orElseThrow();

        Long replyId = replyService.save(
                board.getId()
                , member
                , "replyText"
        );

        //when
        replyService.delete(replyId, member);
        //then
        assertThrows(NoSuchElementException.class,
                () -> replyRepository.findById(replyId).orElseThrow());
    }
}