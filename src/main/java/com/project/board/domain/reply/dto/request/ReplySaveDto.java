package com.project.board.domain.reply.dto.request;

import lombok.Data;

@Data
public class ReplySaveDto {
    private Long boardNo;
    private String replyText;

}
