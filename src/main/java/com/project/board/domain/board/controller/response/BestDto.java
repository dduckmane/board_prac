package com.project.board.domain.board.controller.response;

import com.project.board.domain.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;

@Data
@NoArgsConstructor
public class BestDto {
    private static final String UPLOAD_PATH = "C:\\sl_dev\\upload";
    private String name;
    private Long viewCnt;
    private Long itemId;

    public BestDto(Board board) throws MalformedURLException {
        this.name = board.getTitle();
        this.viewCnt = board.getViewCnt();
        this.itemId= board.getId();

    }
}
