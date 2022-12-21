package com.project.board.domain.board.service;

import com.project.board.domain.board.domain.Address;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.BoardFiles;
import com.project.board.domain.board.domain.UploadFile;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void save(
            Member member
            , int groupId
            , String title
            , String content
            , UploadFile thumbNail
            , Address address
            , List<UploadFile>uploadFiles
            , int price
            , List<String> tag

    ){
        List<BoardFiles> attachFiles = uploadFiles.stream().map(BoardFiles::new).collect(Collectors.toList());

        String renewTag="";
        for (String tagName : tag) { renewTag+=","+tagName;}

        Board saveBoard = Board.write(member, groupId, title, content,thumbNail,address,attachFiles,price,renewTag);
        boardRepository.save(saveBoard);
    }

    @Transactional
    public void update(Long boardId, String content) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        board.update(content);
    }
    @Transactional
    public void delete(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow();
        boardRepository.delete(board);
    }
    @Transactional
    public Optional<Board> findOne(Long boardId, HttpServletResponse response, HttpServletRequest request){
        Board board = boardRepository.findById(boardId).orElseThrow();

        makeViewCount(board,response,request);
        return Optional.ofNullable(board);
    }

    private void makeViewCount(Board board, HttpServletResponse response, HttpServletRequest request) {
        Cookie foundCookie = WebUtils.getCookie(request, "board_" + board.getId());

        if (foundCookie == null) {
            board.plusViewCnt();
            Cookie cookie = new Cookie("board_" + board.getId(), String.valueOf(board.getId()));// 쿠키 생성
            cookie.setMaxAge(60); // 쿠키 수명 설정
            cookie.setPath("/user/board/");
            response.addCookie(cookie);
        }
    }
}
