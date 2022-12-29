package com.project.board.domain.board.domain.boardenum;


import lombok.Getter;

@Getter
public enum Tag {
    MOOD("분위기")
    , PLAY("놀기 좋은")
    , RESERVATION("예약 가능")
    , PRICE("가성비") 
    ;
    private final String description;

    Tag(String description) {
        this.description = description;
    }


}
