package com.project.board.domain.member.domain.searchInfo.searchCnt;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NameInfoAddTest {

    @Test
    void addCnt() {
        NameInfoAdd nameInfoAdd = new NameInfoAdd();

        nameInfoAdd.addCnt("title");
        nameInfoAdd.addCnt("title");
        nameInfoAdd.addCnt("title name");

        nameInfoAdd.getOrderMap().entrySet().stream().forEach(entry->{
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        });
    }
}