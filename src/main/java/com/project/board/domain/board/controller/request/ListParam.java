package com.project.board.domain.board.controller.request;

import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListParam {
    private Integer groupId;
    private String param;

    public Object getParam(){
        if(groupId!=null)return groupId;
        if(param!=null)return param;

        throw new IllegalArgumentException("No Search Param");
    }

    public String getRequest(){
        if(groupId!=null)return "groupId";
        if(param!=null)return "param";

        throw new IllegalArgumentException("No Search RequestParam");
    }

    public Map<String,String> getInfo(){
        Map<String,String> info= new ConcurrentHashMap<>();

        if(groupId!=null) info.put(SearchInfo.CATEGORY, groupId.toString());
        if(param!=null) info.put(SearchInfo.REGION, param);

        return info;
    }
}
