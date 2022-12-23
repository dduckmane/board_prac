package com.project.board.domain.board.controller.request;

import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.board.domain.boardenum.Regions.*;

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
        if (param.equals(SEOUL.toString())) return SEOUL.getDescription();
        if (param.equals(GYEONGGI.toString())) return GYEONGGI.getDescription();
        if (param.equals(INCHEON.toString())) return INCHEON.getDescription();
        if (param.equals(GANG.toString())) return GANG.getDescription();
        if (param.equals(JN.toString())) return JN.getDescription();
        if (param.equals(JS.toString())) return JS.getDescription();
        if (param.equals(GS.toString())) return GS.getDescription();
        if (param.equals(GN.toString())) return GN.getDescription();
        if (param.equals(JEJU.toString())) return JEJU.getDescription();

        throw new IllegalArgumentException();
    }
    public boolean checkParam(){ return groupId != null; }
}
