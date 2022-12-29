package com.project.board.domain.reply.service;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.reply.domain.Reply;
import com.project.board.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    @Transactional
    public Long save(Long boardId, Member member,String replyText){
        Board board = boardRepository.findById(boardId).orElseThrow();
        Reply reply = Reply.write(replyText, board, member);

        Reply saveReply = replyRepository.save(reply);

        return saveReply.getId();
    }
    @Transactional
    public void update(Long replyId, Member member, String replyText){
        Reply reply = replyRepository.findById(replyId).orElseThrow();

        if(reply.checkMySelf(member)) reply.update(replyText);
    }
    @Transactional
    public void delete(Long replyId, Member member){
        Reply reply = replyRepository.findById(replyId).orElseThrow();

        if(reply.checkMySelf(member)) replyRepository.delete(reply);
    }

}
