package com.project.board.domain.member.domain.searchInfo;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.searchCnt.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchInfo {

    public static String CATEGORY="category";
    public static String REGION="regions";
    public static String TAG="tag";
    public static String PRICE="price";
    public static String NAME="name";
    public static String TITLE="title";
    public static String ALL="all";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "searchInfo_id", nullable = false)
    private Long id;
    @Embedded
    private CategoryCnt category=new CategoryCnt();
    @Embedded
    private RegionsCnt regionsCnt=new RegionsCnt();
    @Embedded
    private PriceCnt priceCnt=new PriceCnt();
    @Embedded
    private TagCnt tagCnt=new TagCnt();
    @Embedded
    private NameInfoAdd nameInfoAdd=new NameInfoAdd();

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;


    //member 회원 가입시에 회원 검색 정보 db 생성
    //회원 마다 새로 만들어짐 , 싱글톤 x
    public SearchInfo(Member member) {
        this.member = member;
        member.addSearchInfo(this);
    }

    public void addCnt(Member member, String type, String param){
        if (type.equals("sort")||type.equals("name")) return;
        // 각 카테고리 별로 검색한 횟 수 와 내용을 저장한다.
        // adapter 패턴의 변형
        // type 바탕으로 type 에 맞는 handler 를 가져온다.
        findAddCntHandler(type).addCnt(param);
        //수정된 addCnt 로 다시 바꾼다.
        member.addSearchInfo(this);
    }


    AddCnt findAddCntHandler(String type){
        List<AddCnt> info =new ArrayList<>();

        info.add(priceCnt);
        info.add(regionsCnt);
        info.add(category);
        info.add(tagCnt);
        info.add(nameInfoAdd);

        return info.stream()
                .filter(addCnt -> addCnt.support(type))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException());
    }

    public int getTotalScore(Board board){
        int sum=0;

        sum+= category.getScore(board.getGroupId());
        sum+= priceCnt.getScore(board.getPrice());
        sum+= regionsCnt.getScore(board.getAddress().getRepresentativeArea());
        sum+= tagCnt.getScore(board.getTagSum());
        sum+= nameInfoAdd.getScore(board.getTitle());

        return sum;
    }



}
