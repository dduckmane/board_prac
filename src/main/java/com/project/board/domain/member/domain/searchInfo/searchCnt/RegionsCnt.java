package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private int chungcheongDo;
    @ElementCollection
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        return name.equals(REGION);
    }

    public void addCnt(String region) {
        if(region.equals("Seoul")) Seoul++;
        if(region.equals("gyeonggiDo")) gyeonggiDo++;
        if(region.equals("incheon")) incheon++;
        if(region.equals("gangwonDo")) gangwonDo++;
        if(region.equals("jeollaBukDo")) jeollaBukDo++;
        if(region.equals("jeollaNamDo")) jeollaNamDo++;
        if(region.equals("gyeongsangBukDo")) gyeongsangBukDo++;
        if(region.equals("gyeongsangNamDo")) gyeongsangNamDo++;
        if(region.equals("chungcheongDo")) chungcheongDo++;
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
                ,Integer.toString(chungcheongDo)+"chungcheongDo"
                ,"0"
        };

        Arrays.sort(orders);
        order(0,orders,orderMap,orders.length-1,0);

        return getScoreByGroupId(region);

    }

    private Integer getScoreByGroupId(String region) {
        if(region.equals("Seoul")) return orderMap.get("Seoul");
        if(region.equals("gyeonggiDo")) return orderMap.get("gyeonggiDo");
        if(region.equals("incheon")) return orderMap.get("incheon");
        if(region.equals("gangwonDo")) return orderMap.get("gangwonDo");
        if(region.equals("jeollaBukDo")) return orderMap.get("jeollaBukDo");
        if(region.equals("jeollaNamDo")) return orderMap.get("jeollaNamDo");
        if(region.equals("gyeongsangBukDo")) return orderMap.get("gyeongsangBukDo");
        if(region.equals("gyeongsangNamDo")) return orderMap.get("gyeongsangNamDo");
        if(region.equals("chungcheongDo")) return orderMap.get("chungcheongDo");

        throw new IllegalArgumentException("잘못된 groupId");
    }

}
