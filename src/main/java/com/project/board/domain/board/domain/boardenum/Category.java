package com.project.board.domain.board.domain.boardenum;


import lombok.Getter;

@Getter
public enum Category {
    KOREAN("한식", 1)
    , AMERICA("양식", 2)
    , CHINA("중식", 3)
    , JAPAN("일식", 4)
    ;
    private final String description;
    private final int groupId;

    Category(String description, int groupId) {
        this.description = description;
        this.groupId = groupId;
    }

    public static String getDescription(int groupId) {
        if(groupId== KOREAN.groupId) return KOREAN.getDescription();
        if(groupId== AMERICA.groupId) return AMERICA.getDescription();
        if(groupId== CHINA.groupId) return CHINA.getDescription();
        if(groupId== JAPAN.groupId) return JAPAN.getDescription();

        throw new IllegalArgumentException("No search GroupId");
    }
}
