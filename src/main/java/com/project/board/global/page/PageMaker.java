package com.project.board.global.page;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageMaker {
    private int nowPage;
    private int endPage;
    private int startPage;
    private boolean first;
    private boolean last;
    private int totalPages;

}
