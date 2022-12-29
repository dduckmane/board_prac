package com.project.board.global.helper;

import com.project.board.domain.board.domain.Address;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.UploadFile;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.member.domain.Member;
import com.project.board.global.domainConst.BoardConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.project.board.domain.board.domain.boardenum.Tag.*;
import static com.project.board.global.domainConst.BoardConst.*;

@Component
@Transactional
public class BoardRepoHelper {
    @Autowired
    MemberHelper memberHelper;
    @Autowired
    EntityManager em;

    public void boardListInit(){
        Member member = memberHelper.createMember();
        for (int i = 1; i <=30 ; i++) {
            Board board = Board.write(
                    member
                    , i%4 +1
                    , "title"+i
                    , "content"+i
                    , new UploadFile(UPLOAD_FILE_NAME, STORE_FILE_NAME)
                    , new Address(getRegion(i%9 +1), DETAIL_AREA)
                    , (i%4 +1) * 10000
                    , getTag(i%4 +1)+","+getTag(5-(i%4 +1)));
            em.persist(board);
        }
    }
    String getTag(int i){
        if(i == 1) return MOOD.toString();
        if(i == 2) return PLAY.toString();
        if(i == 3) return RESERVATION.toString();
        if(i == 4) return PRICE.toString();

        throw new IllegalArgumentException();
    }

    String getRegion(int i){
        if(i == 1) return REGION_SEOUL;
        if(i == 2) return REGION_GYEONGGI;
        if(i == 3) return REGION_INCHEON;
        if(i == 4) return REGION_GANG;
        if(i == 5) return REGION_JN;
        if(i == 6) return REGION_JS;
        if(i == 7) return REGION_GS;
        if(i == 8) return REGION_GN;
        if(i == 9) return REGION_JEJU;

        throw new IllegalArgumentException();
    }
}
