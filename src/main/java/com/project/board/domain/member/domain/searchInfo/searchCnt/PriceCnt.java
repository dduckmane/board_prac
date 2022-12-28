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
    private int priceOption1;
    private int priceOption2;
    private int priceOption3;
    private int priceOption4;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        return name.equals(PRICE);
    }
    @Override
    public void addCnt(String price) {
        if(price.equals("10000")) priceOption1++;
        else if(price.equals("20000")) priceOption2++;
        else if(price.equals("30000")) priceOption3++;
        else priceOption4++;
    }
    public int getScore(int price){

        String[] orders ={
                Integer.toString(priceOption1)+"priceOption1"
                ,Integer.toString(priceOption2)+"priceOption2"
                ,Integer.toString(priceOption3)+"priceOption3"
                ,Integer.toString(priceOption4)+"priceOption4"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(price);
    }

    private Integer getScoreByGroupId(int price) {
        if(price<=10000) return orderMap.get("priceOption1");
        else if(price>10000&price<=20000) return orderMap.get("priceOption2");
        else if(price>20000&price<=30000) return orderMap.get("priceOption3");
        else return orderMap.get("priceOption4");
    }
}
