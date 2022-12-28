package com.project.board.global.helper;

import com.project.board.domain.board.domain.Address;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.project.board.global.domainConst.BoardConst.*;

@Component
@Transactional
public class BoardHelper {
    @Autowired
    EntityManager em;
    @Autowired
    MemberHelper memberHelper;
    public Board createBoard(){
        Board board = Board.write(
                memberHelper.createMember()
                , GROUPID_1
                , TITLE
                , CONTENT
                , new UploadFile(UPLOAD_FILE_NAME, STORE_FILE_NAME)
                , new Address(REGION_SEOUL, DETAIL_AREA)
                , PRICE_1
                , TAG_PRICE);

        em.persist(board);

        return board;
    }
}
