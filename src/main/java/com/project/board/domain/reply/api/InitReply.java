package com.project.board.domain.reply.api;//package com.project.board.board.controller;
//
//import com.project.board.board.domain.Board;
//import com.project.board.board.repository.BoardRepository;
//import com.project.board.member.domain.Member;
//import com.project.board.reply.domain.Reply;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class InitReply {
//    private final InitBoardService initBoardService;
//
//    @PostConstruct
//    public void init(){
//        initBoardService.init();
//    }
//    @Component
//    static class InitBoardService{
//        @PersistenceContext
//        private EntityManager em;
//        @Transactional
//        public void init(){
//            for (int i = 1; i <=4 ; i++) {
//                Board board = new Board();
//                Member member = new Member();
//                em.persist(member);
//                em.persist(board);
//                for (int j = 1; j <=30 ; j++) {
//                    Reply reply = new Reply();
//                    em.persist(reply);
//                    reply.setBoard(board);
//                    reply.setMember(member);
//                }
//            }
//            em.flush();
//            em.clear();
//            }
//
//        }
//    }
//
