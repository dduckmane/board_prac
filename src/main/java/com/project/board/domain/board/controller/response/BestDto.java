package com.project.board.domain.board.controller.response;

import com.project.board.domain.board.domain.Board;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;

@Data
public class BestDto {
    private String name;
    private Long viewCnt;
    private Long itemId;

    public BestDto(Board board)  {
        this.name = board.getTitle();
        this.viewCnt = board.getViewCnt();
        this.itemId= board.getId();

    }
}
