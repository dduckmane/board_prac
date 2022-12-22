package com.project.board.domain.board.controller.request;

import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.member.domain.Member;
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
        return checkParam() ? groupId: param;
    }

    public String getRequest(){
        return checkParam() ? "groupId": "param";
    }

    public Map<String,String> getInfo(){
        Map<String,String> info= new ConcurrentHashMap<>();

        if(groupId!=null) info.put(SearchInfo.CATEGORY, groupId.toString());
        if(param!=null) info.put(SearchInfo.REGION, param);

        return info;
    }

    public String getTitle(Member member){

        return checkParam() ? Category.getDescription(groupId) : getName(member);

    }

    public String getName(Member member){
        if(param.equals("choice")) return member.getName()+" 님의 찜 목록";
        if (param.equals("recommend")) return member.getName()+" 님의 전용 맞춤";

        throw new IllegalArgumentException();
    }
    public boolean checkParam(){ return groupId != null; }
}
