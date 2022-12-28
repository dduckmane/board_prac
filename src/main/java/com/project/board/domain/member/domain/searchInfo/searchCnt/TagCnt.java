package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.board.domain.boardenum.Tag.*;
import static com.project.board.domain.member.domain.searchInfo.SearchInfo.TAG;
import static com.project.board.global.util.OrderUtils.order;

@NoArgsConstructor
@Embeddable
@Data
public class TagCnt implements AddCnt {
    private int tagOption1;
    private int tagOption2;
    private int tagOption3;
    private int tagOption4;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        return name.equals(TAG);
    }
    @Override
    public void addCnt(String tag) {
        if(tag.contains(MOOD.toString())) tagOption1++;
        if(tag.contains(PRICE.toString())) tagOption2++;
        if(tag.contains(RESERVATION.toString())) tagOption3++;
        if(tag.contains(PLAY.toString())) tagOption4++;
    }
    public int getScore(String tag){

        String[] orders ={
                Integer.toString(tagOption1)+"tagOption1"
                ,Integer.toString(tagOption2)+"tagOption2"
                ,Integer.toString(tagOption3)+"tagOption3"
                ,Integer.toString(tagOption4)+"tagOption4"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(tag);
    }

    private Integer getScoreByGroupId(String tagSum) {
        int sum=0;

        if(tagSum.contains(MOOD.toString()))  sum+=orderMap.get("tagOption1");
        if(tagSum.contains(PRICE.toString())) sum+= orderMap.get("tagOption2");
        if(tagSum.contains(RESERVATION.toString())) sum+= orderMap.get("tagOption3");
        if(tagSum.contains(PLAY.toString())) sum+= orderMap.get("tagOption4");

        return sum;
    }
}
