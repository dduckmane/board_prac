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
    private int categoryOption1;
    private int categoryOption2;
    private int categoryOption3;
    private int categoryOption4;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();


    @Override
    public Boolean support(String name) {
        return name.equals(CATEGORY);
    }
    @Override
    public void addCnt(String region){
        if(region.equals("1")) categoryOption1++;
        if(region.equals("2")) categoryOption2++;
        if(region.equals("3")) categoryOption3++;
        if(region.equals("4")) categoryOption4++;
    }

    public int getScore(int groupId){
        // ex) 3categoryOption1 4categoryOption2 9categoryOption3 2categoryOption4
        String[] orders ={
                Integer.toString(categoryOption1)+"categoryOption1"
                ,Integer.toString(categoryOption2)+"categoryOption2"
                ,Integer.toString(categoryOption3)+"categoryOption3"
                ,Integer.toString(categoryOption4)+"categoryOption4"
                ,"0"
        };
        // String 을 이용한 정렬
        // 2categoryOption4 3categoryOption1 4categoryOption2 9categoryOption3
        Arrays.sort(orders);
        // map 에 카테고리별 점수가 담김
        // (categoryOption4,0) (categoryOption1,1) (categoryOption2,2) (categoryOption3,3)
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(groupId);
    }

    private Integer getScoreByGroupId(int groupId) {
        if(groupId ==1) return orderMap.get("categoryOption1");
        if(groupId ==2) return orderMap.get("categoryOption2");
        if(groupId ==3) return orderMap.get("categoryOption3");
        if(groupId ==4) return orderMap.get("categoryOption4");

        throw new IllegalArgumentException("wrong option");
    }
}
