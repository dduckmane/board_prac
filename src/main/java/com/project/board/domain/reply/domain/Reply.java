package com.project.board.domain.reply.domain;

import com.project.board.domain.base.BaseEntity;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;
    private String replyText;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Reply(String replyText){
        this.replyText=replyText;
    }
    public static Reply write(
            String replyText
            ,Board board
            ,Member member
    ){
        Reply reply = new Reply();

        reply.replyText=replyText;
        reply.board=board;
        reply.member=member;

        board.getReplies().add(reply);
        member.getReplies().add(reply);

        return reply;
    }
    public void update(String replyText){
        this.replyText=replyText;
    }

    public boolean checkMySelf(Member member) {
        return member.getId() == member.getId();
    }

}