package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.member.domain.searchInfo.SearchInfo.CATEGORY;
import static com.project.board.global.util.OrderUtils.order;

@Data
@Embeddable
@NoArgsConstructor
public class CategoryCnt implements AddCnt {
    private int korean;
    private int japan;
    private int china;
    private int america;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();


    @Override
    public Boolean support(String name) {
        return name.equals(CATEGORY);
    }
    @Override
    public void addCnt(String region){
        if(region.equals("1")) korean++;
        if(region.equals("2")) japan++;
        if(region.equals("3")) china++;
        if(region.equals("4")) america++;
    }

    public int getScore(int groupId){

        String[] orders ={
                Integer.toString(korean)+"korean"
                ,Integer.toString(japan)+"japan"
                ,Integer.toString(china)+"china"
                ,Integer.toString(america)+"america"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(groupId);
    }

    private Integer getScoreByGroupId(int groupId) {
        if(groupId ==1) return orderMap.get("korean");
        if(groupId ==2) return orderMap.get("america");
        if(groupId ==3) return orderMap.get("china");
        if(groupId ==4) return orderMap.get("japan");
        throw new IllegalArgumentException("잘못된 groupId");
    }
}
