package com.project.board.domain.reply.repository;

import com.project.board.domain.reply.domain.Reply;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.board.domain.board.domain.QBoard.board;
import static com.project.board.domain.member.domain.QMember.member;
import static com.project.board.domain.reply.domain.QReply.reply;


public class ReplyRepositoryImpl implements ReplyRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public ReplyRepositoryImpl(EntityManager em) {
        this.queryFactory=new JPAQueryFactory(em);
    }

    @Override
    public Page<Reply> searchAll(Long boardId,Pageable pageable) {
        List<Reply> results = queryFactory
                .selectFrom(reply)
                .leftJoin(reply.board, board).fetchJoin()
                .leftJoin(reply.member, member).fetchJoin()
                .where(board.id.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> CountQuery = queryFactory
                .select(reply.count())
                .where(board.id.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .from(reply);

        return PageableExecutionUtils.getPage(results,pageable,CountQuery::fetchOne);
    }
}
