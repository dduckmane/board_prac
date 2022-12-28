package com.project.board.domain.member.domain;

import com.project.board.domain.base.BaseTimeEntity;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.reply.domain.Reply;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String role; //ROLE_USER, ROLE_ADMIN
    private String provider;
    private String providerId;
    @OneToMany(mappedBy = "member"
            , fetch = FetchType.LAZY
            ,orphanRemoval = true
            ,cascade = CascadeType.ALL)
    List <Reply> replies=new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    List<Long> choiceBoard=new ArrayList<>();

    @OneToOne(mappedBy = "member"
            , fetch = FetchType.LAZY
            ,orphanRemoval = true
            ,cascade = CascadeType.ALL)
    private SearchInfo searchInfo;

    public Member(String name){
        this.name=name;
    }
    public void addSearchInfo(SearchInfo searchInfo){
        this.searchInfo=searchInfo;
    }

    @Builder
    public Member(
            String name
            , String password
            , String username
            , String email
            , String role
            , String provider
            , String providerId
    ) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void choiceBoard(Long boardId) {
        Long findBoardId = choiceBoard
                .stream()
                .filter(id -> id == boardId).
                findFirst()
                .orElse(null);

        if(findBoardId==null) choiceBoard.add(boardId);
        else choiceBoard.remove(boardId);
    }
}
