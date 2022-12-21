package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.member.domain.searchInfo.SearchInfo.PRICE;
import static com.project.board.global.util.OrderUtils.order;

@NoArgsConstructor
@Embeddable
@Data
public class PriceCnt implements AddCnt {
    private int one;
    private int two;
    private int three;
    private int four;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        return name.equals(PRICE);
    }
    @Override
    public void addCnt(String price) {
        if(price.equals("10000")) one++;
        else if(price.equals("20000")) two++;
        else if(price.equals("30000")) three++;
        else four++;
    }
    public int getScore(int price){

        String[] orders ={
                Integer.toString(one)+"one"
                ,Integer.toString(two)+"two"
                ,Integer.toString(three)+"three"
                ,Integer.toString(four)+"four"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(price);
    }

    private Integer getScoreByGroupId(int price) {
        if(price<=10000) return orderMap.get("one");
        else if(price>10000&price<=20000) return orderMap.get("two");
        else if(price>20000&price<=30000) return orderMap.get("three");
        else return orderMap.get("four");
    }
}
