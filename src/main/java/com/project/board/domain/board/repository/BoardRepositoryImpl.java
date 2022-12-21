package com.project.board.domain.board.repository;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.search.BoardSearchCondition;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.board.domain.board.domain.QBoard.board;
import static com.project.board.domain.member.domain.QMember.member;
import static org.apache.logging.log4j.util.Strings.isEmpty;


public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<Board> searchAllCondition(int groupId, BoardSearchCondition searchCondition, Pageable pageable) {

        List<Board> result = queryFactory
                .select(board).distinct()
                .from(board)
                .join(board.member,member).fetchJoin()
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , board.groupId.eq(groupId)
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count()).distinct()
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , board.groupId.eq(groupId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }

    @Override
    public Page<Board> searchByRegions(String regions, BoardSearchCondition searchCondition, Pageable pageable) {

        List<Board> result = queryFactory
                .select(board).distinct()
                .from(board)
                .join(board.member,member).fetchJoin()
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , board.address.representativeArea.eq(regions)
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count()).distinct()
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , board.address.representativeArea.eq(regions)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }
    @Override
    public Page<Board> searchAll(BoardSearchCondition searchCondition, Pageable pageable) {

        List<Board> result = queryFactory
                .select(board).distinct()
                .from(board)
                .join(board.member,member).fetchJoin()
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count()).distinct()
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }
    @Override
    public Page<Board> searchByChoice(BoardSearchCondition searchCondition, Pageable pageable) {

        List<Board> result = queryFactory
                .select(board).distinct()
                .from(board)
                .join(board.member,member).fetchJoin()
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , board.id.in(member.choiceBoard)
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count()).distinct()
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , board.id.in(member.choiceBoard)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }

    public Page<Board> searchBestInfo(Pageable pageable) {
        List<Board> result = queryFactory
                .selectFrom(board)
                .orderBy(board.viewCnt.desc())
                .offset(pageable.getOffset())
                .limit(6l)
                .fetch();

        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count())
                .from(board)
                .offset(pageable.getOffset())
                .limit(6l)
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }



    private OrderSpecifier<?> boardSort(Pageable page) {
        if (!page.getSort().isEmpty()) {
            for (Sort.Order order : page.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "id":
                        return new OrderSpecifier(direction, board.id);
                    case "titleASC":
                        return new OrderSpecifier(Order.ASC, board.title);
                    case "titleDESC":
                        return new OrderSpecifier(Order.DESC, board.title);
                    case "createdDateASC":
                        return new OrderSpecifier(Order.ASC, board.createdDate);
                    case "createdDateDESC":
                        return new OrderSpecifier(Order.DESC, board.createdDate);
                    case "viewCnt":
                        return new OrderSpecifier(Order.DESC, board.viewCnt);
                }
            }
        }
        return null;
    }
    private BooleanExpression usernameEq(String username){
        return !isEmpty(username) ? member.username.contains(username) : null;
    }
    private BooleanExpression titleEq(String title){
        return !isEmpty(title) ? board.title.contains(title) : null;
    }
    private BooleanExpression usernameOrTitleEq(String all){
        return !isEmpty(all) ? board.title.contains(all).or(member.username.contains(all)) : null;
    }
    private BooleanExpression filteringTag(String tag){
        return !isEmpty(tag) ? board.tagSum.contains(tag) : null;
    }
    private BooleanExpression filteringPrice(String price){
        if(isEmpty(price)) return null;
        if(price.equals("10000")) return board.price.loe(10000);
        else if(price.equals("20000")) return board.price.gt(10000).and(board.price.loe(20000));
        else if(price.equals("30000")) return board.price.gt(20000).and(board.price.loe(30000));
        else return board.price.gt(30000);
    }
}
