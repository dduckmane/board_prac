package com.project.board.domain.reply.dto;

import com.project.board.domain.reply.domain.Reply;
import lombok.Data;

import java.time.format.DateTimeFormatter;
@Data
public class ReplyDto {
    private Long id;
    private String replyText;
    private String replyWriter;
    private String createDate;

    public ReplyDto(Reply reply) {

        this.id=reply.getId();
        this.replyText = reply.getReplyText();
        this.replyWriter = reply.getMember().getName();//이거 나중에 수정--> 시큐리티로 이용
        this.createDate = reply.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
