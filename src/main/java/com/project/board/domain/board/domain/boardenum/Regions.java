package com.project.board.domain.board.domain.boardenum;

import lombok.Getter;

@Getter
public enum Regions {
    SEOUL("서울")
    , GYEONGGI("경기")
    , INCHEON("인천")
    , GANG("강원도")
    , JN("전라 북도")
    , JS("전라남도")
    , GS("경상 북도")
    , GN("경상 남도")
    , JEJU("제주도")
    ;

    private final String description;

    Regions(String description) {
        this.description = description;
    }
}
