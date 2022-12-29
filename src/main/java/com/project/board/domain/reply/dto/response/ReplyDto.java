package com.project.board.domain.reply.dto.response;

import com.project.board.domain.reply.domain.Reply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.format.DateTimeFormatter;
@Data
public class ReplyDto {
    private Long id;
    private String replyText;
    private String replyWriter;
    private String createDate;
    private String username;

    public ReplyDto(Reply reply) {

        this.id=reply.getId();
        this.replyText = reply.getReplyText();

        this.replyWriter = reply
                .getMember()
                .getName();

        this.createDate = reply
                .getCreatedDate()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        this.username = reply.getMember().getUsername();
    }
}
