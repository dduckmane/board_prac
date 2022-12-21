package com.project.board.domain.board.controller.adapter.dto;

import com.project.board.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendListDto implements Comparable<RecommendListDto>{
    private int score;
    private Board board;

    @Override
    public int compareTo(RecommendListDto o) {
        return o.getScore()-score;
    }
}
