package com.project.board.global.helper;

import com.project.board.domain.member.domain.Member;
import com.project.board.global.config.CustomBCryPasswordEncoder;
import com.project.board.global.domainConst.MemberConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.project.board.global.domainConst.MemberConst.*;

@Component
@Transactional
public class MemberHelper {
    @Autowired
    EntityManager em;

    @Autowired
    CustomBCryPasswordEncoder encoder;

    public Member createMember(){
        Member member = Member.builder()
                .username(USERNAME)
                .password(encoder.encode(PASSWORD))
                .name(NAME)
                .email(EMAIL)
                .role(ROLE)
                .provider(PROVIDER)
                .providerId(PROVIDERID)
                .build();

        em.persist(member);

        return member;
    }


}
