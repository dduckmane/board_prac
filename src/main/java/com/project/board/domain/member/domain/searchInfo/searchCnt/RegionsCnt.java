package com.project.board.domain.member.domain.searchInfo.searchCnt;

import com.project.board.domain.board.domain.ENUM.Regions;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.board.domain.ENUM.Regions.*;
import static com.project.board.domain.member.domain.searchInfo.SearchInfo.REGION;
import static com.project.board.global.util.OrderUtils.order;

@NoArgsConstructor
@Embeddable
@Data
public class RegionsCnt implements AddCnt {
    private int Seoul;
    private int gyeonggiDo;
    private int incheon;
    private int gangwonDo;
    private int jeollaBukDo;
    private int jeollaNamDo;
    private int gyeongsangBukDo;
    private int gyeongsangNamDo;
    private int jeju;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        return name.equals(REGION);
    }

    public void addCnt(String region) {
        if(region.equals(SEOUL.toString())) Seoul++;
        if(region.equals(GYEONGGI.toString())) gyeonggiDo++;
        if(region.equals(INCHEON.toString())) incheon++;
        if(region.equals(GANG.toString())) gangwonDo++;
        if(region.equals(JN.toString())) jeollaBukDo++;
        if(region.equals(JS.toString())) jeollaNamDo++;
        if(region.equals(GS.toString())) gyeongsangBukDo++;
        if(region.equals(GN.toString())) gyeongsangNamDo++;
        if(region.equals(JEJU.toString())) jeju++;
    }

    public int getScore(String region){

        String[] orders ={
                Integer.toString(Seoul)+"Seoul"
                ,Integer.toString(gyeonggiDo)+"gyeonggiDo"
                ,Integer.toString(incheon)+"incheon"
                ,Integer.toString(gangwonDo)+"gangwonDo"
                ,Integer.toString(jeollaBukDo)+"jeollaBukDo"
                ,Integer.toString(jeollaNamDo)+"jeollaNamDo"
                ,Integer.toString(gyeongsangBukDo)+"gyeongsangBukDo"
                ,Integer.toString(gyeongsangNamDo)+"gyeongsangNamDo"
                ,Integer.toString(jeju)+"jeju"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(region);

    }

    private Integer getScoreByGroupId(String region) {
        if(region.equals(SEOUL.toString())) return orderMap.get("Seoul");
        if(region.equals(GYEONGGI.toString())) return orderMap.get("gyeonggiDo");
        if(region.equals(INCHEON.toString())) return orderMap.get("incheon");
        if(region.equals(GANG.toString())) return orderMap.get("gangwonDo");
        if(region.equals(JN.toString())) return orderMap.get("jeollaBukDo");
        if(region.equals(JS.toString())) return orderMap.get("jeollaNamDo");
        if(region.equals(GS.toString())) return orderMap.get("gyeongsangBukDo");
        if(region.equals(GN.toString())) return orderMap.get("gyeongsangNamDo");
        if(region.equals(JEJU.toString())) return orderMap.get("jeju");

        throw new IllegalArgumentException("잘못된 groupId");
    }

}
