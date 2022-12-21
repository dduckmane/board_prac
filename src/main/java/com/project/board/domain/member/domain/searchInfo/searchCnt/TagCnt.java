package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

        if(tag.contains("atmosphere")) atmosphere++;
        else if(tag.contains("money")) money++;
        else if(tag.contains("reservation")) reservation++;
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

        if(tag.contains("atmosphere"))  sum+=orderMap.get("atmosphere");
        if(tag.equals("money")) sum+= orderMap.get("money");
        if(tag.equals("reservation")) sum+= orderMap.get("reservation");
        if(tag.equals("play")) sum+= orderMap.get("play");

        return sum;
    }
}
