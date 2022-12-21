package com.project.board.domain.board.controller;//package com.project.board.board.controller;
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
//public class InitBoard {
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
//            int one=1;
//            int two=2;
//            for (int i = 1; i <= 26; i++) {
//                int selected=i%2==0? two:one;
//                String username = String.valueOf((int) ((Math.random() * 10000) % 10));
//                Board board = new Board(String.valueOf(i));
//                Member member = new Member(username);
//                board.setMember(member);
//                board.setGroupId(selected);
//                for (int j = 1; j < 2; j++) {
//                    Reply reply = new Reply(String.valueOf((int) ((Math.random() * 10000) % 10)));
//                    em.persist(reply);
//                    member.setReplies(reply);
//                    board.setReplies(reply);
//                }
//                em.persist(member);
//                em.persist(board);
//            }
//            for (int i = 1; i <=16 ; i++) {
//                Reply reply = new Reply(String.valueOf((int) ((Math.random() * 10000) % 10)));
//                em.persist(reply);
//                Member member = new Member("홍길동");
//                em.persist(member);
//                Board board = em.find(Board.class, 1L);
//                reply.setBoard(board);
//                reply.setMember(member);
//            }
//            em.flush();
//            em.clear();
//        }
//    }
//}