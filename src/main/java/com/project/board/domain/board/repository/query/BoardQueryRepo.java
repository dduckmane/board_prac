package com.project.board.domain.board.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BoardQueryRepo {
    private final JPAQueryFactory queryFactory;
    public BoardQueryRepo(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


}
