package com.project.board.domain.member.domain.searchInfo;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.searchCnt.*;
import com.project.board.global.domainConst.BoardConst;
import com.project.board.global.testInit.MemberTestInit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchInfoTest extends MemberTestInit {

    SearchInfo searchInfo =new SearchInfo();
    @Test
    void addCnt() {
        //given
        Member member = memberHelper.createMember();

        String type = SearchInfo.CATEGORY;
        String param = String.valueOf(Category.KOREAN.getGroupId());

        //when
        searchInfo.addCnt(member, type, param);

        //then
        int categoryOption1 = member.getSearchInfo().getCategory().getCategoryOption1();

        assertThat(categoryOption1).isEqualTo(1);
    }

    @Test
    void findAddCntHandler() {
        //given
        String type1 = SearchInfo.CATEGORY;
        String type2 = SearchInfo.REGION;
        String type3 = SearchInfo.TAG;
        String type4 = SearchInfo.PRICE;
        String type5 = SearchInfo.TITLE;
        String type6 = SearchInfo.ALL;

        //when
        AddCnt addCntHandler1 = searchInfo.findAddCntHandler(type1);
        AddCnt addCntHandler2 = searchInfo.findAddCntHandler(type2);
        AddCnt addCntHandler3 = searchInfo.findAddCntHandler(type3);
        AddCnt addCntHandler4 = searchInfo.findAddCntHandler(type4);
        AddCnt addCntHandler5 = searchInfo.findAddCntHandler(type5);
        AddCnt addCntHandler6 = searchInfo.findAddCntHandler(type6);

        //then
        assertAll(
                () -> assertThat(addCntHandler1.getClass().toString()).isEqualTo(new CategoryCnt().getClass().toString())
                ,() -> assertThat(addCntHandler2.getClass().toString()).isEqualTo(new RegionsCnt().getClass().toString())
                ,() -> assertThat(addCntHandler3.getClass().toString()).isEqualTo(new TagCnt().getClass().toString())
                ,() -> assertThat(addCntHandler4.getClass().toString()).isEqualTo(new PriceCnt().getClass().toString())
                ,() -> assertThat(addCntHandler5.getClass().toString()).isEqualTo(new NameInfoAdd().getClass().toString())
                ,() -> assertThat(addCntHandler6.getClass().toString()).isEqualTo(new NameInfoAdd().getClass().toString())
        );
    }

    @Test
    void getTotalScore() {
        //given
        Board board = boardHelper.createBoard();
        Member member = memberHelper.createMember();

        searchInfo.addCnt(member, SearchInfo.CATEGORY, String.valueOf(board.getGroupId()));
        searchInfo.addCnt(member, SearchInfo.REGION, board.getAddress().getRepresentativeArea());
        searchInfo.addCnt(member, SearchInfo.PRICE, String.valueOf(board.getPrice()));
        searchInfo.addCnt(member, SearchInfo.TAG, board.getTagSum());
        searchInfo.addCnt(member, SearchInfo.TITLE, board.getTitle());

        //when
        int totalScore = searchInfo.getTotalScore(board);
        //then
        assertThat(totalScore).isEqualTo(6);
    }
}