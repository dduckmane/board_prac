package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.board.domain.boardenum.Regions.*;
import static com.project.board.domain.member.domain.searchInfo.SearchInfo.REGION;
import static com.project.board.global.util.OrderUtils.order;

@NoArgsConstructor
@Embeddable
@Data
public class RegionsCnt implements AddCnt {
    private int regionsOption1;
    private int regionsOption2;
    private int regionsOption3;
    private int regionsOption4;
    private int regionsOption5;
    private int regionsOption6;
    private int regionsOption7;
    private int regionsOption8;
    private int regionsOption9;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        return name.equals(REGION);
    }

    public void addCnt(String region) {
        if(region.equals(SEOUL.toString())) regionsOption1++;
        if(region.equals(GYEONGGI.toString())) regionsOption2++;
        if(region.equals(INCHEON.toString())) regionsOption3++;
        if(region.equals(GANG.toString())) regionsOption4++;
        if(region.equals(JN.toString())) regionsOption5++;
        if(region.equals(JS.toString())) regionsOption6++;
        if(region.equals(GS.toString())) regionsOption7++;
        if(region.equals(GN.toString())) regionsOption8++;
        if(region.equals(JEJU.toString())) regionsOption9++;
    }

    public int getScore(String region){

        String[] orders ={
                Integer.toString(regionsOption1)+"regionsOption1"
                ,Integer.toString(regionsOption2)+"regionsOption2"
                ,Integer.toString(regionsOption3)+"regionsOption3"
                ,Integer.toString(regionsOption4)+"regionsOption4"
                ,Integer.toString(regionsOption5)+"regionsOption5"
                ,Integer.toString(regionsOption6)+"regionsOption6"
                ,Integer.toString(regionsOption7)+"regionsOption7"
                ,Integer.toString(regionsOption8)+"regionsOption8"
                ,Integer.toString(regionsOption9)+"regionsOption9"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(region);

    }

    private Integer getScoreByGroupId(String region) {
        if(region.equals(SEOUL.toString())) return orderMap.get("regionsOption1");
        if(region.equals(GYEONGGI.toString())) return orderMap.get("regionsOption2");
        if(region.equals(INCHEON.toString())) return orderMap.get("regionsOption3");
        if(region.equals(GANG.toString())) return orderMap.get("regionsOption4");
        if(region.equals(JN.toString())) return orderMap.get("regionsOption5");
        if(region.equals(JS.toString())) return orderMap.get("regionsOption6");
        if(region.equals(GS.toString())) return orderMap.get("regionsOption7");
        if(region.equals(GN.toString())) return orderMap.get("regionsOption8");
        if(region.equals(JEJU.toString())) return orderMap.get("regionsOption9");

        throw new IllegalArgumentException("잘못된 name");
    }

}
