package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.board.domain.ENUM.Tag.*;
import static com.project.board.domain.member.domain.searchInfo.SearchInfo.TAG;
import static com.project.board.global.util.OrderUtils.order;

@NoArgsConstructor
@Embeddable
@Data
public class TagCnt implements AddCnt {
    private int atmosphere;
    private int money;
    private int reservation;
    private int play;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        return name.equals(TAG);
    }
    @Override
    public void addCnt(String tag) {

        if(tag.contains(MOOD.toString())) atmosphere++;
        else if(tag.contains(PRICE.toString())) money++;
        else if(tag.contains(RESERVATION.toString())) reservation++;
        else play++;
    }
    public int getScore(String tag){

        String[] orders ={
                Integer.toString(atmosphere)+"atmosphere"
                ,Integer.toString(money)+"money"
                ,Integer.toString(reservation)+"reservation"
                ,Integer.toString(play)+"play"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(tag);
    }

    private Integer getScoreByGroupId(String tag) {
        int sum=0;

        if(tag.contains(MOOD.toString()))  sum+=orderMap.get("atmosphere");
        if(tag.equals(PRICE.toString())) sum+= orderMap.get("money");
        if(tag.equals(RESERVATION.toString())) sum+= orderMap.get("reservation");
        if(tag.equals(PLAY.toString())) sum+= orderMap.get("play");

        return sum;
    }
}
