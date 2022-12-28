package com.project.board.domain.board.controller.request.search;

import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.member.domain.searchInfo.SearchInfo.*;
import static com.project.board.domain.member.domain.searchInfo.SearchInfo.PRICE;

@Data
@Builder
public class BoardSearchCondition {
    private String name;
    private String title;
    private String all;
    private String price;
    private String tag;

    public Map<String,String> getInfo(){
        Map<String,String> info= new ConcurrentHashMap<>();

        if(name!=null) info.put(NAME, name);
        if(title!=null) info.put(TITLE, title);
        if(all!=null) info.put(ALL, all);
        if(price!=null) info.put(PRICE, price);
        if(tag!=null) info.put(TAG, tag);

        return info;
    }
}
