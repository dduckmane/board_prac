package com.project.board.domain.board.dto.request;

import com.project.board.domain.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardUpdateForm {
    private Long id;
    private String title;
    private String content;

    public BoardUpdateForm(Board board) {
        this.id=board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
