package com.project.board.domain.board.controller.adapter.dto;

import com.project.board.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//추천 리스트를 추출을 위한 dto
//각각의 board 와 회원별 각 board 에 대한 점수를 넣어 놓는다.
public class RecommendListDto implements Comparable<RecommendListDto>{
    private int score;
    private Board board;

    //객체의 비교는 score 로 하겠다.
    @Override
    public int compareTo(RecommendListDto o) {
        return o.getScore()-score;
    }
}
