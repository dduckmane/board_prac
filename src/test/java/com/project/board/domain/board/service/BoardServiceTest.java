package com.project.board.domain.board.service;

import com.project.board.domain.board.domain.Address;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.UploadFile;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.member.domain.Member;
import com.project.board.global.domainConst.BoardConst;
import com.project.board.global.testInit.BoardTestInit;
import com.project.board.global.testInit.TestInit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest extends TestInit {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Test
    void save() {
        //given
        Member member = memberHelper.createMember();
        int groupId = Category.KOREAN.getGroupId();
        String title = "title";
        String content = "content";
        UploadFile thumbNail = new UploadFile(BoardConst.UPLOAD_FILE_NAME,BoardConst.STORE_FILE_NAME);
        Address address = new Address(Regions.SEOUL.toString(), BoardConst.DETAIL_AREA);
        int price = 10000;
        List<String> tag = Arrays.asList(Tag.MOOD.toString(), Tag.PLAY.toString());

        //when
        Long boardId = boardService.save(
                member
                , groupId
                , title
                , content
                , thumbNail
                , address
                , price
                , tag);
        //then
        Board board = boardRepository.findById(boardId).orElseThrow();

        assertAll(
                () -> assertThat(board.getMember()).isEqualTo(member)
                , () -> assertThat(board.getGroupId()).isEqualTo(groupId)
                , () -> assertThat(board.getTitle()).isEqualTo(title)
                , () -> assertThat(board.getContent()).isEqualTo(content)
                , () -> assertThat(board.getThumbNail()).usingRecursiveComparison().isEqualTo(thumbNail)
                , () -> assertThat(board.getAddress()).usingRecursiveComparison().isEqualTo(address)
                , () -> assertThat(board.getPrice()).isEqualTo(price)
                , () -> assertThat(board.getTagSum()).isEqualTo(","+Tag.MOOD.toString()+","+Tag.PLAY.toString())
        );
    }

    @Test
    void update() {
        //given
        Board board = boardHelper.createBoard();
        String updateTitle = "title";
        String updateContent = "content";
        UploadFile updateThumbNail = new UploadFile(BoardConst.UPLOAD_FILE_NAME,BoardConst.STORE_FILE_NAME);
        Address updateAddress = new Address(Regions.SEOUL.toString(), BoardConst.DETAIL_AREA);
        int updatePrice = 10000;
        List<String> tag = Arrays.asList(Tag.MOOD.toString(), Tag.PLAY.toString());

        //when
        boardService.update(
                board.getId()
                , updateTitle
                , updateContent
                , updateThumbNail
                , updateAddress
                , updatePrice
                , tag);
        //then
        Board findBoard = boardRepository.findById(board.getId()).orElseThrow();

        assertAll(
                () -> assertThat(findBoard.getTitle()).isEqualTo(updateTitle)
                , () -> assertThat(findBoard.getContent()).isEqualTo(updateContent)
                , () -> assertThat(findBoard.getThumbNail()).usingRecursiveComparison().isEqualTo(updateThumbNail)
                , () -> assertThat(findBoard.getAddress()).usingRecursiveComparison().isEqualTo(updateAddress)
                , () -> assertThat(findBoard.getPrice()).isEqualTo(updatePrice)
                , () -> assertThat(findBoard.getTagSum()).isEqualTo(","+Tag.MOOD.toString()+","+Tag.PLAY.toString())
        );
    }

    @Test
    void delete() {
        //given
        Board board = boardHelper.createBoard();
        //when
        boardService.delete(board.getId());
        //then
        assertThrows(NoSuchElementException.class,
                () -> boardRepository.findById(board.getId()).orElseThrow());
    }
}