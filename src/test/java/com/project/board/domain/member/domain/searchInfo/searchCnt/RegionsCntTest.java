package com.project.board.domain.member.domain.searchInfo.searchCnt;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.global.domainConst.BoardConst;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegionsCntTest extends TestInit {
    RegionsCnt regionsCnt=new RegionsCnt();
    @Test
    void support() {
        //given
        String name = SearchInfo.REGION;
        //when
        Boolean support = regionsCnt.support(name);
        //then
        assertThat(support).isEqualTo(true);
    }
    @Test
    @Order(1)
    void addCnt() {
        //given
        String region1= BoardConst.REGION_SEOUL;
        String region1_1= BoardConst.REGION_SEOUL;
        String region1_2= BoardConst.REGION_SEOUL;
        String region2= BoardConst.REGION_GYEONGGI;
        String region2_1= BoardConst.REGION_GYEONGGI;
        String region3= BoardConst.REGION_INCHEON;
        String region4= BoardConst.REGION_GANG;
        String region5= BoardConst.REGION_GS;
        String region6= BoardConst.REGION_GN;
        String region7= BoardConst.REGION_JN;
        String region7_1= BoardConst.REGION_JN;
        String region7_2= BoardConst.REGION_JN;
        String region8= BoardConst.REGION_JS;
        String region9= BoardConst.REGION_JEJU;
        //when
        regionsCnt.addCnt(region1);
        regionsCnt.addCnt(region1_1);
        regionsCnt.addCnt(region1_2);
        regionsCnt.addCnt(region2);
        regionsCnt.addCnt(region2_1);
        regionsCnt.addCnt(region3);
        regionsCnt.addCnt(region4);
        regionsCnt.addCnt(region5);
        regionsCnt.addCnt(region6);
        regionsCnt.addCnt(region7);
        regionsCnt.addCnt(region7_1);
        regionsCnt.addCnt(region7_2);
        regionsCnt.addCnt(region8);
        regionsCnt.addCnt(region9);

        //then
        assertAll(
            ()-> assertThat(regionsCnt.getRegionsOption1()).isEqualTo(3)
            , ()-> assertThat(regionsCnt.getRegionsOption2()).isEqualTo(2)
            , ()-> assertThat(regionsCnt.getRegionsOption3()).isEqualTo(1)
            , ()-> assertThat(regionsCnt.getRegionsOption4()).isEqualTo(1)
            , ()-> assertThat(regionsCnt.getRegionsOption5()).isEqualTo(3)
            , ()-> assertThat(regionsCnt.getRegionsOption6()).isEqualTo(1)
            , ()-> assertThat(regionsCnt.getRegionsOption7()).isEqualTo(1)
            , ()-> assertThat(regionsCnt.getRegionsOption8()).isEqualTo(1)
            , ()-> assertThat(regionsCnt.getRegionsOption9()).isEqualTo(1)
        );

    }
    @Test
    @Order(2)
    void getScore() {
        //given
        Board board = boardHelper.createBoard();
        String representativeArea = board.getAddress().getRepresentativeArea();
        //when
        int score_1 = regionsCnt.getScore(representativeArea);
        int score_2 = regionsCnt.getScore(BoardConst.REGION_GYEONGGI);
        int score_3 = regionsCnt.getScore(BoardConst.REGION_INCHEON);
        int score_4 = regionsCnt.getScore(BoardConst.REGION_GANG);
        int score_5 = regionsCnt.getScore(BoardConst.REGION_JN);
        int score_6 = regionsCnt.getScore(BoardConst.REGION_JS);
        int score_7 = regionsCnt.getScore(BoardConst.REGION_GN);
        int score_8 = regionsCnt.getScore(BoardConst.REGION_GS);
        int score_9 = regionsCnt.getScore(BoardConst.REGION_JEJU);
        //then
        assertAll(
                ()-> assertThat(score_1).isEqualTo(3)
                , ()-> assertThat(score_2).isEqualTo(2)
                , ()-> assertThat(score_3).isEqualTo(1)
                , ()-> assertThat(score_4).isEqualTo(1)
                , ()-> assertThat(score_5).isEqualTo(3)
                , ()-> assertThat(score_6).isEqualTo(1)
                , ()-> assertThat(score_7).isEqualTo(1)
                , ()-> assertThat(score_8).isEqualTo(1)
                , ()-> assertThat(score_9).isEqualTo(1)
        );
    }
}